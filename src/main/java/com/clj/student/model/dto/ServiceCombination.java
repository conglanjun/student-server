package com.clj.student.model.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ServiceCombination {
    private Map<String, Integer> statisticsNumByServiceType;
    private List<ServiceData> serviceDataList;
}
