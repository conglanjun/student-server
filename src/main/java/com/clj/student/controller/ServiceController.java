package com.clj.student.controller;

import com.clj.student.model.dto.ServiceData;
import com.clj.student.model.dto.ServiceRequest;
import com.clj.student.model.dto.ServiceTypeRequest;
import com.clj.student.model.po.Service;
import com.clj.student.model.po.ServiceType;
import com.clj.student.model.vo.ServiceResponse;
import com.clj.student.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ServiceController {

    @Autowired
    private ToolService toolService;

    @GetMapping("serviceList")
    public ServiceResponse serviceList(@RequestParam(required = false) Long creatorId, @RequestParam(required = false) Long maintainerId) {
        List<ServiceData> serviceDataList = toolService.serviceList(creatorId, maintainerId);
        return new ServiceResponse(serviceDataList, 200, "service list successfully!");
    }

    @PostMapping("service/save")
    public ServiceResponse serviceSave(@RequestBody ServiceRequest request) {
        Service save = toolService.save(request);
        return new ServiceResponse(200, "save service successfully!", save);
    }

    @PostMapping("service/update")
    public ServiceResponse serviceUpdate(@RequestBody ServiceRequest request) {
        Service save = toolService.update(request);
        return new ServiceResponse(200, "save service successfully!", save);
    }

    @DeleteMapping("service/delete/{id}")
    public ServiceResponse serviceDelete(@PathVariable Long id) {
        toolService.delete(id);
        return new ServiceResponse(200, "delete service successfully!");
    }

    @GetMapping("service/{id}")
    public ServiceResponse service(@PathVariable Long id) {
        ServiceData service = toolService.getService(id);
        return new ServiceResponse(200, "get service successfully!", service);
    }

    @PostMapping("serviceType/save")
    public ServiceResponse serviceTypeSave(@RequestBody ServiceTypeRequest serviceTypeRequest) {
        ServiceType serviceType = toolService.saveType(serviceTypeRequest);
        return new ServiceResponse(200, "save service type successfully!", serviceType);
    }

    @GetMapping("serviceTypeList")
    public ServiceResponse serviceTypeList() {
        List<ServiceType> serviceTypeList = toolService.serviceTypeList();
        return new ServiceResponse(200, serviceTypeList, "list service type successfully!");
    }

    @PostMapping("serviceType/delete")
    public ServiceResponse serviceTypeDelete(@RequestBody ServiceType serviceType) {
        toolService.deleteType(serviceType);
        return new ServiceResponse(200, "delete service type successfully!");
    }

    @PostMapping("serviceType/update")
    public ServiceResponse serviceTypeUpdate(@RequestBody ServiceType serviceType) {
        ServiceType st = toolService.updateService(serviceType);
        return new ServiceResponse(200, "update service type successfully!", st);
    }
}
