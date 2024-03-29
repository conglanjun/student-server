package com.clj.student.cols;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DetailData {
//    List<RowRenderData> evaluationList;
    private List<Instance> dataBaseList;

    private Map<String, List<DataBase>> rowInstanceData;
}
