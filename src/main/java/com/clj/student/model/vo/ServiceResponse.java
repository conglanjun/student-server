package com.clj.student.model.vo;

import com.clj.student.model.po.ServiceType;
import com.clj.student.model.dto.ServiceCombination;
import com.clj.student.model.dto.ServiceData;
import com.clj.student.model.dto.ServiceTypeStatisticsData;
import com.clj.student.model.po.Service;
import lombok.Data;

import java.util.List;

@Data
public class ServiceResponse {
    private int code;
    private String message;
    private List<Service> serviceList;
    private List<ServiceData> serviceDataList;
    private Service service;
    private ServiceData serviceData;
    private ServiceCombination serviceCombination;
    private ServiceTypeStatisticsData serviceTypeStatisticsData;

    private ServiceType serviceType;
    private List<ServiceType> serviceTypeList;

    public ServiceResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceResponse(int code, String message, Service service) {
        this.code = code;
        this.message = message;
        this.service = service;
    }

    public ServiceResponse(int code, String message, ServiceData serviceData) {
        this.code = code;
        this.message = message;
        this.serviceData = serviceData;
    }

    public ServiceResponse(int code, String message, List<Service> serviceList) {
        this.code = code;
        this.message = message;
        this.serviceList = serviceList;
    }

    public ServiceResponse(List<ServiceData> serviceDataList, int code, String message) {
        this.code = code;
        this.message = message;
        this.serviceDataList = serviceDataList;
    }

    public ServiceResponse(int code, String message, ServiceType serviceType) {
        this.code = code;
        this.message = message;
        this.serviceType = serviceType;
    }

    public ServiceResponse(int code, List<ServiceType> serviceTypeList, String message) {
        this.code = code;
        this.message = message;
        this.serviceTypeList = serviceTypeList;
    }

    public ServiceResponse(int code, String message, ServiceCombination serviceCombination) {
        this.code = code;
        this.message = message;
        this.serviceCombination = serviceCombination;
    }

    public ServiceResponse(int code, String message, ServiceTypeStatisticsData serviceTypeStatisticsData) {
        this.code = code;
        this.message = message;
        this.serviceTypeStatisticsData = serviceTypeStatisticsData;
    }
    

}
