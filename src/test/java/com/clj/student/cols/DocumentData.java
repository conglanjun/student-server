package com.clj.student.cols;
import lombok.Data;

@Data
public class DocumentData {
    private String document;
    private DetailData detailTable;
    private String comment;
}
