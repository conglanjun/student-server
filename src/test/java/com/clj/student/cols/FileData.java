package com.clj.student.cols;

import com.deepoove.poi.expression.Name;
import lombok.Data;

@Data
public class FileData {
    @Name("document_data")
    private DocumentData documentData;
}
