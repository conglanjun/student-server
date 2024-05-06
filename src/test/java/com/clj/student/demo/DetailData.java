package com.clj.student.demo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DetailData {
//    List<RowRenderData> evaluationList;
    private List<DataBaseV2> dataBaseList;
    private Map<String, DataBaseV2> comprehensiveMap;
}
