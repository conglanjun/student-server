package com.clj.student.service;

import com.clj.student.dao.RoomRepository;
import com.clj.student.dao.ServiceRepository;
import com.clj.student.dao.ServiceTypeRepository;
import com.clj.student.dao.UserRepository;
import com.clj.student.model.dto.ServiceCombination;
import com.clj.student.model.dto.ServiceData;
import com.clj.student.model.dto.ServiceRequest;
import com.clj.student.model.dto.ServiceTypeRequest;
import com.clj.student.model.dto.ServiceTypeStatistics;
import com.clj.student.model.dto.ServiceTypeStatisticsData;
import com.clj.student.model.po.Room;
import com.clj.student.model.po.Service;
import com.clj.student.model.po.ServiceType;
import com.clj.student.model.po.User;
import com.clj.student.utils.ModelConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class ToolService {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;

    private final Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public List<ServiceData> serviceList(Long creatorId, Long maintainerId, Long dormitoryManagerId, String status, String fixStatus) {
        List<ServiceData> serviceDataList = new ArrayList<>();
        List<Service> serviceList = null;
        if ((creatorId == null || creatorId == 0) && (maintainerId == null || maintainerId == 0) && (dormitoryManagerId == null || dormitoryManagerId == 0)) {
            if (status == null || status.isEmpty()) {
                serviceList = serviceRepository.findAllByOrderByCreateTimeDesc();
            } else {
                serviceList = serviceRepository.findAllByStatusOrderByCreateTimeDesc(status);
            }
        } else if (creatorId != null && creatorId > 0){
            if ((status == null || status.isEmpty()) && (fixStatus == null || fixStatus.isEmpty())) {
                serviceList = serviceRepository.findAllByCreatorIdOrderByCreateTimeDesc(creatorId);
            } else if (fixStatus != null && !fixStatus.isEmpty()) {
                List<String> statusList = new ArrayList<>();
                if (fixStatus.equals("NOTFIX")) {
                    statusList.add("SUBMITTED");
                    statusList.add("PENDINGORDER");
                    statusList.add("RECEIVEDORDER");
                    statusList.add("REJECTORDER");
                } else if (fixStatus.equals("FIXED")) {
                    statusList.add("HANDLING");
                    statusList.add("DONE");
                    statusList.add("FINISHED");
                }
                if (!statusList.isEmpty()) {
                    serviceList = serviceRepository.findAllByCreatorIdAndStatusListOrderByCreateTimeDesc(creatorId, statusList);
                }
            } else {
                serviceList = serviceRepository.findAllByCreatorIdAndStatusOrderByCreateTimeDesc(creatorId, status);
            }
        } else if (maintainerId != null && maintainerId > 0) {
            if (status == null || status.isEmpty()) {
                serviceList = serviceRepository.findAllByMaintainerIdOrderByCreateTimeDesc(maintainerId);
            } else {
                serviceList = serviceRepository.findAllByMaintainerIdAndStatusOrderByCreateTimeDesc(maintainerId, status);
            }
        } else if (dormitoryManagerId != null && dormitoryManagerId > 0) {
            // query building id with user id
            Optional<User> findUserById = userRepository.findById(dormitoryManagerId);
            if (findUserById.isEmpty()) {
                return null;
            }
            User user = findUserById.get();
            // query room id with building id
            List<Room> roomList = roomRepository.findAllByBuildingId(user.getRoom().getBuildingId());
            List<Long> roomIds = new ArrayList<>();
            for (Room r: roomList) {
                roomIds.add(r.getId());
            }
            if (fixStatus != null && !fixStatus.isEmpty()) {
                List<String> statusList = new ArrayList<>();
                if (fixStatus.equals("NOTFIX")) {
                    statusList.add("SUBMITTED");
                    statusList.add("PENDINGORDER");
                    statusList.add("RECEIVEDORDER");
                    statusList.add("REJECTORDER");
                } else if (fixStatus.equals("FIXED")) {
                    statusList.add("HANDLING");
                    statusList.add("DONE");
                    statusList.add("FINISHED");
                }
                if (!statusList.isEmpty()) {
                    serviceList = serviceRepository.findByRoomIdsAndStatusList(roomIds, statusList);
                }
            } else if (status == null || status.isEmpty()) {
                serviceList = serviceRepository.findByRoomIds(roomIds);
            } else {
                serviceList = serviceRepository.findByRoomIdsAndStatus(roomIds, status);
            }
        }
        if (serviceList == null) {
            return null;
        }
        StringBuffer dt = new StringBuffer();
        for (int i = 0; i < serviceList.size(); i++) {
            Service s = serviceList.get(i);
            dt.append("标题：").append(s.getTitle());
            if (!s.getStatus().isEmpty()) {
                ServiceStatus serviceStatus = ServiceStatus.valueOfStatus(s.getStatus());
                dt.append("，  状态：").append(serviceStatus.getDisplayStatus());
                if (serviceStatus.getStatus().equals(ServiceStatus.HANDLING.getStatus()) || serviceStatus.getStatus().equals(ServiceStatus.DONE.getStatus()) || serviceStatus.getStatus().equals(ServiceStatus.FINISHED.getStatus())) {
                    if (s.getMaintainer() != null && !s.getMaintainer().getName().isEmpty()) {
                        dt.append("，  维修者：").append(s.getMaintainer().getName());
                    }
                }
            }
            ServiceData sd = ModelConvert.ServiceConvertServiceData(s, dt.toString());
            serviceDataList.add(sd);
            dt.setLength(0);
        }
        return serviceDataList;
    }

    public Service save(ServiceRequest request) {
        request.setCreateTime(Calendar.getInstance().getTime());
        Optional<ServiceType> findStById = serviceTypeRepository.findById(request.getTypeId());
        if (findStById.isEmpty()) {
            return null;
        }
        ServiceType serviceType = findStById.get();
        Optional<User> userById = userRepository.findById(request.getCreatorId());
        if (userById.isEmpty()) {
            return null;
        }
        Optional<Room> roomById = roomRepository.findById(request.getRoomId());
        if (roomById.isEmpty()) {
            return null;
        }
        Room room = roomById.get();
        User user = userById.get();
        Service service = ModelConvert.ServiceRequestConvertService(request, serviceType, user, room);
        log.info("save servie:" + service);
        return serviceRepository.save(service);
    }

    public ServiceData getService(Long id) {
        Optional<Service> byId = serviceRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        Service s = byId.get();
//        ServiceData sd = new ServiceData();
//        sd.setId(s.getId());
//        sd.setTitle(s.getTitle());
//        sd.setDetail(s.getDetail());
//        sd.setCreateTime(s.getCreateTime());
//        String formatCreateTime = format.format(s.getCreateTime());
//        sd.setDisplayCreateTime(formatCreateTime);
//        sd.setStatus(s.getStatus());
//        sd.setDisplayStatus(ServiceStatus.valueOfStatus(s.getStatus()).getDisplayStatus());
//        sd.setCreator(s.getCreator());
//        sd.setMaintainer(s.getMaintainer());
//        sd.setRate(s.getRate());
//        sd.setComment(s.getComment());
//        sd.setServiceType(s.getServiceType());
        return ModelConvert.ServiceConvertServiceData(s, s.getTitle());
    }

    public ServiceType saveType(ServiceTypeRequest serviceTypeRequest) {
        serviceTypeRequest.setCreateTime(Calendar.getInstance().getTime());
        ServiceType serviceType = ModelConvert.ServiceTypeRequestConvertServiceType(serviceTypeRequest);
        log.info("save serviceType:" + serviceType);
        return serviceTypeRepository.save(serviceType);
    }

    public List<ServiceType> serviceTypeList() {
        return serviceTypeRepository.findAll();
    }

    public void deleteType(ServiceType serviceType) {
        serviceTypeRepository.delete(serviceType);
    }

    public ServiceType updateService(ServiceType serviceType) {
        Optional<ServiceType> byId = serviceTypeRepository.findById(serviceType.getId());
        if (byId.isEmpty()) {
            return null;
        }
        ServiceType st = byId.get();
        st.setName(serviceType.getName());
        st.setCreateTime(Calendar.getInstance().getTime());
        ServiceType save = serviceTypeRepository.save(st);
        return save;
    }

    public Service update(ServiceRequest request) {
        Optional<Service> serviceById = serviceRepository.findById(request.getId());
        if (serviceById.isEmpty()) {
            return null;
        }
        Service service = serviceById.get();
        if (request.getMaintainerId() != null) {
            Optional<User> userById = userRepository.findById(request.getMaintainerId());
            if (userById.isEmpty()) {
                return null;
            }
            User user = userById.get();
            service.setMaintainer(user);
        }
        if (request.getStatus() != null) {
            service.setStatus(request.getStatus());
        }
        if (request.getRate() != null) {
            service.setRate(request.getRate());
        }
        if (request.getComment() != null) {
            service.setComment(request.getComment());
        }
        if (request.getRejectDetail() != null) {
            service.setRejectDetail(request.getRejectDetail());
        }
        return serviceRepository.save(service);
    }

    public void delete(Long id) {
        serviceRepository.delete(new Service(id));
    }

    public List<ServiceData> serviceListByDispatchStatus(String dispatchStatus) {
        List<ServiceData> serviceDataList = new ArrayList<>();
        List<String> statusList = new ArrayList<>();
        if (dispatchStatus == null || dispatchStatus.isEmpty()) {
            return serviceDataList;
        }
        if (dispatchStatus.equals("PENDINGDISPATCH")) {
            statusList.add("SUBMITTED");
        } else if (dispatchStatus.equals("DISPATCHED")) {
            statusList.add("PENDINGORDER");
            statusList.add("RECEIVEDORDER");
            statusList.add("REJECTORDER");
            statusList.add("HANDLING");
            statusList.add("DONE");
            statusList.add("FINISHED");
        }
        List<Service> serviceList = serviceRepository.findAllByDispatchStatus(statusList);
        if (serviceList == null) {
            return null;
        }
        StringBuffer dt = new StringBuffer();
        for (int i = 0; i < serviceList.size(); i++) {
            Service s = serviceList.get(i);
            dt.append("标题：").append(s.getTitle());
            if (!s.getStatus().isEmpty()) {
                ServiceStatus serviceStatus = ServiceStatus.valueOfStatus(s.getStatus());
                dt.append("  状态：").append(serviceStatus.getDisplayStatus());
                if (serviceStatus.getStatus().equals(ServiceStatus.HANDLING.getStatus()) || serviceStatus.getStatus().equals(ServiceStatus.DONE.getStatus()) || serviceStatus.getStatus().equals(ServiceStatus.FINISHED.getStatus())) {
                    if (s.getMaintainer() != null && !s.getMaintainer().getName().isEmpty()) {
                        dt.append("  维修者：").append(s.getMaintainer().getName());
                    }
                }
            }
            ServiceData sd = ModelConvert.ServiceConvertServiceData(s, dt.toString());
            serviceDataList.add(sd);
            dt.setLength(0);
        }
        return serviceDataList;
    }

    public List<ServiceData> serviceListByFinishedStatus(Long maintainerId, String finishedStatus, String commentStatus) {
        List<ServiceData> serviceDataList = new ArrayList<>();
        List<String> statusList = new ArrayList<>();
        List<Service> serviceList;
        if (finishedStatus != null && finishedStatus.equals("NOTFINISHED")) {
            statusList.add("PENDINGORDER");
            statusList.add("RECEIVEDORDER");
            statusList.add("HANDLING");
        } else if (finishedStatus != null && finishedStatus.equals("FINISHED")) {
            statusList.add("DONE");
            statusList.add("FINISHED");
        } else if (commentStatus != null && commentStatus.equals("FINISHED")) {
            statusList.add("FINISHED");
        }
        if (statusList == null || statusList.isEmpty()) {
            return null;
        }
        if (maintainerId != null && maintainerId > 0) {
            serviceList = serviceRepository.findAllByMaintainerIdAndFinishedStatus(maintainerId, statusList);
        } else {
            serviceList = serviceRepository.findAllByFinishedStatus(statusList);
        }
        if (serviceList == null) {
            return null;
        }
        StringBuffer dt = new StringBuffer();
        for (int i = 0; i < serviceList.size(); i++) {
            Service s = serviceList.get(i);
            dt.append("标题：").append(s.getTitle());
            if (!s.getStatus().isEmpty()) {
                ServiceStatus serviceStatus = ServiceStatus.valueOfStatus(s.getStatus());
                dt.append("  状态：").append(serviceStatus.getDisplayStatus());
                if (serviceStatus.getStatus().equals(ServiceStatus.HANDLING.getStatus()) || serviceStatus.getStatus().equals(ServiceStatus.DONE.getStatus()) || serviceStatus.getStatus().equals(ServiceStatus.FINISHED.getStatus())) {
                    if (s.getMaintainer() != null && !s.getMaintainer().getName().isEmpty()) {
                        dt.append("  维修者：").append(s.getMaintainer().getName());
                    }
                }
            }
            ServiceData sd = ModelConvert.ServiceConvertServiceData(s, dt.toString());
            serviceDataList.add(sd);
            dt.setLength(0);
        }
        return serviceDataList;
    }

    public ServiceCombination orderStatisticsByServiceType(Long maintainerId) {
        ServiceCombination serviceCombination = new ServiceCombination();
        List<ServiceData> serviceDataList = new ArrayList<>();
        // query all service with maintainer id
        List<Service> serviceList = serviceRepository.findAllByMaintainerIdOrderByCreateTimeDesc(maintainerId);
        // all service types
        List<ServiceType> serviceTypes = serviceTypeRepository.findAll();
        Map<String, Integer> serviceMap = new HashMap<>();
        for (ServiceType st: serviceTypes) {
            serviceMap.put(st.getName(), 0);
        }
        StringBuffer dt = new StringBuffer();
        for (Service s: serviceList) {
            String key = s.getServiceType().getName();
            dt.append("标题：").append(s.getTitle());
            if (!s.getStatus().isEmpty()) {
                ServiceStatus serviceStatus = ServiceStatus.valueOfStatus(s.getStatus());
                dt.append("，  状态：").append(serviceStatus.getDisplayStatus());
                if (serviceStatus.getStatus().equals(ServiceStatus.HANDLING.getStatus()) || serviceStatus.getStatus().equals(ServiceStatus.DONE.getStatus()) || serviceStatus.getStatus().equals(ServiceStatus.FINISHED.getStatus())) {
                    if (s.getMaintainer() != null && !s.getMaintainer().getName().isEmpty()) {
                        dt.append("，  维修者：").append(s.getMaintainer().getName());
                    }
                }
            }
            ServiceData sd = ModelConvert.ServiceConvertServiceData(s, dt.toString());
            Integer num = serviceMap.get(key);
            serviceMap.put(key, ++num); 
            serviceDataList.add(sd);
            dt.setLength(0);
        }
        serviceCombination.setServiceDataList(serviceDataList);
        serviceCombination.setStatisticsNumByServiceType(serviceMap);
        return serviceCombination;
    }

    public List<ServiceData> serviceListByServiceType(Long maintainerId, Long serviceTypeId) {
        List<ServiceData> serviceDataList = new ArrayList<>();
        List<Service> serviceList = null;
        if (serviceTypeId == null || serviceTypeId == 0) {
            serviceList = serviceRepository.findAllByMaintainerIdOrderByCreateTimeDesc(maintainerId);
        } else {
            serviceList = serviceRepository.findAllByMaintainerIdAndTypeIdOrderByCreateTimeDesc(maintainerId, serviceTypeId);
        }
        StringBuffer dt = new StringBuffer();
        for (int i = 0; i < serviceList.size(); i++) {
            Service s = serviceList.get(i);
            dt.append("标题：").append(s.getTitle());
            if (!s.getStatus().isEmpty()) {
                ServiceStatus serviceStatus = ServiceStatus.valueOfStatus(s.getStatus());
                dt.append("  状态：").append(serviceStatus.getDisplayStatus());
                if (serviceStatus.getStatus().equals(ServiceStatus.HANDLING.getStatus()) || serviceStatus.getStatus().equals(ServiceStatus.DONE.getStatus()) || serviceStatus.getStatus().equals(ServiceStatus.FINISHED.getStatus())) {
                    if (s.getMaintainer() != null && !s.getMaintainer().getName().isEmpty()) {
                        dt.append("  维修者：").append(s.getMaintainer().getName());
                    }
                }
            }
            ServiceData sd = ModelConvert.ServiceConvertServiceData(s, dt.toString());
            serviceDataList.add(sd);
            dt.setLength(0);
        }
        return serviceDataList;
    }

    public ServiceTypeStatisticsData typeStatistics() {
        ServiceTypeStatisticsData ret = new ServiceTypeStatisticsData();
        ret.setData(new ArrayList<>());
        List<ServiceType> serviceTypes = serviceTypeRepository.findAll();
        List<String> statusList = new ArrayList<>();
        statusList.add("DONE");
        statusList.add("FINISHED");
        for (ServiceType st: serviceTypes) {
            ServiceTypeStatistics sts = new ServiceTypeStatistics();
            List<Service> serviceList = serviceRepository.findAllByDoneStatusAndType(statusList, st.getId());
            sts.setName(st.getName());
            sts.setValue(serviceList.size());
            ret.getData().add(sts);
        }
        return ret;
    }
}
