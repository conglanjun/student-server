package com.example.demo;

import com.deepoove.poi.expression.Name;
import lombok.Data;

@Data
public class DocumentData {
    private String document;
    @Name("detail_table")
    private DetailData detailTable;
}
