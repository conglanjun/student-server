package com.clj.student.utils;

import com.clj.student.model.dto.*;
import com.clj.student.model.po.*;
import com.clj.student.service.ServiceStatus;

import java.text.Format;
import java.text.SimpleDateFormat;

public class ModelConvert {

    private static final Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Student StudentRequestConvertStudent(StudentRequest studentRequest) {
        Student s = new Student();
        s.setCreateTime(studentRequest.getCreateTime());
        s.setId(studentRequest.getId());
        s.setName(studentRequest.getName());
        s.setPhone(studentRequest.getPhone());
        s.setPassword(studentRequest.getPassword());
        return s;
    }

    public static User UserDataConvertUser(UserData userData) {
        User u = new User();
        u.setCreateTime(userData.getCreateTime());
        u.setId(userData.getId());
        u.setName(userData.getName());
        u.setPhone(userData.getPhone());
        u.setPassword(userData.getPassword());
        u.setRoomId(userData.getRoomId());
        u.setRoleId(userData.getRoleId().intValue());
        return u;
    }

    public static Service ServiceRequestConvertService(ServiceRequest serviceRequest, ServiceType serviceType, User user, Room room) {
        Service s = new Service();
        s.setCreateTime(serviceRequest.getCreateTime());
        s.setId(serviceRequest.getId());
        s.setTitle(serviceRequest.getTitle());
        s.setDetail(serviceRequest.getDetail());
        s.setCreator(user);
        s.setStatus(serviceRequest.getStatus());
        s.setServiceType(serviceType);
        s.setRoom(room);
        if (serviceRequest.getStatus() == null || serviceRequest.getStatus().isEmpty()) {
            s.setStatus(ServiceStatus.SUBMITTED.getStatus());
        } else {
            s.setStatus(serviceRequest.getStatus());
        }
        return s;
    }

    public static ServiceType ServiceTypeRequestConvertServiceType(ServiceTypeRequest serviceTypeRequest) {
        ServiceType s = new ServiceType();
        s.setCreateTime(serviceTypeRequest.getCreateTime());
        s.setId(serviceTypeRequest.getId());
        s.setName(serviceTypeRequest.getName());

        return s;
    }

    public static ServiceData ServiceConvertServiceData(Service s, String displayTitle) {
        ServiceData sd = new ServiceData();
        sd.setId(s.getId());
        sd.setTitle(s.getTitle());
        sd.setDisplayTitle(displayTitle);
        sd.setDetail(s.getDetail());
        sd.setCreateTime(s.getCreateTime());
        String formatCreateTime = format.format(s.getCreateTime());
        sd.setDisplayCreateTime(formatCreateTime);
        sd.setStatus(s.getStatus());
        sd.setDisplayStatus(ServiceStatus.valueOfStatus(s.getStatus()).getDisplayStatus());
        sd.setCreator(s.getCreator());
        sd.setMaintainer(s.getMaintainer());
        sd.setRate(s.getRate());
        sd.setComment(s.getComment());
        sd.setServiceType(s.getServiceType());
        sd.setRejectDetail(s.getRejectDetail());
        sd.setRoom(s.getRoom());
        if (s.getRoom() == null) {
            sd.setDisplayRoomInfo("无");
        } else {
            sd.setDisplayRoomInfo(s.getRoom().getBuilding().getName() + "楼 " + s.getRoom().getName());
        }
        return sd;
    }

    public static ConsumptionData ConsumptionConvertConsumptionData(Consumption c) {
        ConsumptionData cd = new ConsumptionData();
        cd.setId(c.getId());
        cd.setType(c.getType());
        cd.setCreateTime(c.getCreateTime());
        String formatCreateTime = format.format(c.getCreateTime());
        cd.setDisplayCreateTime(formatCreateTime);
        cd.setInventory(c.getInventory());
        return cd;
    }

    public static Consumption ConsumptionDataConvertConsumption(ConsumptionData cd, Consumption cp) {
        Consumption c = new Consumption();
        c.setId(cd.getId() == null? cp.getId():cd.getId());
        c.setType(cd.getType() == null? cp.getType():cd.getType());
        c.setCreateTime(cd.getCreateTime() == null? cp.getCreateTime():cd.getCreateTime());
        c.setInventory(cd.getInventory() == null? cp.getInventory():cd.getInventory());
        return c;
    }

    public static BuildingData BuildingConvertBuildingData(Building building) {
        BuildingData bd = new BuildingData();
        bd.setId(building.getId());
        bd.setName(building.getName());
        bd.setCreateTime(building.getCreateTime());
        bd.setDescription(building.getDescription());
        if (building.getDescription() != null && building.getDescription().length() > 10) {
            bd.setDisplayDescription(building.getDescription().substring(0, 10) + "...");
        } else {
            bd.setDisplayDescription(building.getDescription());
        }
        return bd;
    }

    public static Building BuildingDataConvertBuilding(BuildingData buildingData, Building building) {
        Building b = new Building();
        b.setId(buildingData.getId() == null? building.getId() : buildingData.getId());
        b.setName(buildingData.getName() == null? building.getName() : buildingData.getName());
        b.setCreateTime(buildingData.getCreateTime() == null? building.getCreateTime(): buildingData.getCreateTime());
        b.setDescription(buildingData.getDescription() == null? building.getDescription() : buildingData.getDescription());
        return b;
    }

    public static UserData UserConvertUserData(User u) {
        UserData ud = new UserData();
        ud.setId(u.getId());
        ud.setName(u.getName());
        ud.setPhone(u.getPhone());
        ud.setCreateTime(u.getCreateTime());
        ud.setPassword(u.getPassword());
        ud.setIdentity(u.getIdentity());
        ud.setRole(u.getRole());
        ud.setRoom(u.getRoom());
        return ud;
    }

    public static RoomData RoomConvertRoomData(Room r) {
        RoomData rd = new RoomData();
        rd.setId(r.getId());
        rd.setName(r.getName());
        rd.setDescription(r.getDescription());
        if (r.getDescription().length() > 10) {
            rd.setDisplayDescription(r.getDescription().substring(0, 10) + "...");
        } else {
            rd.setDisplayDescription(r.getDescription());
        }
        rd.setCreateTime(r.getCreateTime());
        String formatCreateTime = format.format(r.getCreateTime());
        rd.setDisplayCreateTime(formatCreateTime);
        return rd;
    }

    public static MessageData MessageConvertMessageData(Message m) {
        MessageData md = new MessageData();
        md.setId(m.getId());
        md.setTitle(m.getTitle());
        md.setCreateTime(m.getCreateTime());
        String formatCreateTime = format.format(m.getCreateTime());
        md.setDisplayCreateTime(formatCreateTime);
        md.setMessage(m.getMessage());
        md.setUser(m.getUser());
        if (m.getMessage().length() > 10) {
            md.setDisplayMessage(m.getMessage().substring(0, 10) + "...");
        } else {
            md.setDisplayMessage(m.getMessage());
        }
        return md;
    }

    public static Message MessageDataConvertMessage(MessageData messageData) {
        Message message = new Message();
        message.setId(messageData.getId());
        message.setTitle(messageData.getTitle());
        message.setMessage(messageData.getMessage());
        message.setCreateTime(messageData.getCreateTime());
        message.setUser(messageData.getUser());
        return message;
    }

    public static Comment CommentDataConvertComment(CommentData commentData) {
        Comment comment = new Comment();
        comment.setId(commentData.getId());
        comment.setArticleId(commentData.getArticleId());
        comment.setComment(commentData.getComment());
        comment.setCommentUserId(commentData.getCommentUserId());
        comment.setCommentUser(commentData.getCommentUser());
        comment.setContent(commentData.getContent());
        comment.setCreateTime(commentData.getCreateTime());
        comment.setLike(commentData.getLike());
        comment.setMessage(commentData.getMessage());
        comment.setParentId(commentData.getParentId());
        comment.setStatus(commentData.getStatus());
        comment.setUpdateTime(commentData.getUpdateTime());
        return comment;
    }

    public static CommentData CommentConvertCommentData(Comment comment) {
        CommentData commentData = new CommentData();
        commentData.setId(comment.getId());
        commentData.setArticleId(comment.getArticleId());
        commentData.setComment(comment.getComment());
        commentData.setCommentUserId(comment.getCommentUserId());
        commentData.setCommentUser(comment.getCommentUser());
        commentData.setContent(comment.getContent());
        commentData.setCreateTime(comment.getCreateTime());
        String formatCreateTime = format.format(comment.getCreateTime());
        commentData.setDisplayCreateTime(formatCreateTime);
        commentData.setLike(comment.getLike());
        commentData.setMessage(comment.getMessage());
        commentData.setParentId(comment.getParentId());
        commentData.setStatus(comment.getStatus());
        String formatUpdateTime = format.format(comment.getUpdateTime());
        commentData.setDisplayUpdateTime(formatUpdateTime);
        commentData.setUpdateTime(comment.getUpdateTime());
        return commentData;
    }

    public static ConsumptionRecord ConsumptionRecordDataConvertConsumptionRecord(ConsumptionRecordData crd, ConsumptionRecord consumptionRecord) {
        ConsumptionRecord cr = new ConsumptionRecord();
        cr.setId(crd.getId());
        cr.setConsumptionId(crd.getConsumptionId());
        cr.setConsumptionNumber(crd.getConsumptionNumber());
        cr.setCreateTime(crd.getCreateTime());
        cr.setCreatorId(crd.getCreatorId());
        return cr;
    }

    public static ConsumptionRecordData ConsumptionRecordConvertConsumptionRecordData(ConsumptionRecord cr){
        ConsumptionRecordData crd = new ConsumptionRecordData();
        crd.setId(cr.getId());
        String formatCreateTime = format.format(cr.getCreateTime());
        crd.setDisplayCreateTime(formatCreateTime);
        crd.setConsumptionId(cr.getConsumptionId());
        crd.setConsumption(cr.getConsumption());
        crd.setConsumptionNumber(cr.getConsumptionNumber());
        crd.setCreatorId(cr.getCreatorId());
        crd.setCreator(cr.getCreator());
        return crd;
    }
}
