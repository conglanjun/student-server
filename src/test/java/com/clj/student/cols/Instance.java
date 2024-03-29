package com.clj.student.cols;

import java.util.List;

import lombok.Data;

@Data
public class Instance {
    private String name;
    private String col1;
    private String col2;
    List<DataBase> projectScoreList;
}
