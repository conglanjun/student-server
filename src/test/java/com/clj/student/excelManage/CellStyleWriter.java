package com.clj.student.excelManage;

import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.util.CollectionUtils;

import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;

public class CellStyleWriter extends AbstractCellStyleStrategy {
    
    private WriteCellStyle headWriteCellStyle;
    private List<WriteCellStyle> contentWriteCellStyleList;

    public CellStyleWriter() {
    }

    public CellStyleWriter(WriteCellStyle headWriteCellStyle,
        List<WriteCellStyle> contentWriteCellStyleList) {
        this.headWriteCellStyle = headWriteCellStyle;
        this.contentWriteCellStyleList = contentWriteCellStyleList;
    }

    public CellStyleWriter(WriteCellStyle headWriteCellStyle, WriteCellStyle contentWriteCellStyle) {
        this.headWriteCellStyle = headWriteCellStyle;
        if (contentWriteCellStyle != null) {
            this.contentWriteCellStyleList = ListUtils.newArrayList(contentWriteCellStyle);
        }
    }

    @Override
    protected void setHeadCellStyle(CellWriteHandlerContext context) {
        if (stopProcessing(context) || headWriteCellStyle == null) {
            return;
        }
        WriteCellData<?> cellData = context.getFirstCellData();
        WriteCellStyle.merge(headWriteCellStyle, cellData.getOrCreateStyle());
    }

    @Override
    protected void setContentCellStyle(CellWriteHandlerContext context) {
        if (stopProcessing(context) || CollectionUtils.isEmpty(contentWriteCellStyleList)) {
            return;
        }
        WriteCellData<?> cellData = context.getFirstCellData();
        WriteCellStyle writeCellStyle = cellData.getOrCreateStyle();
        // 经费 拨款 红色
        if (context.getColumnIndex() > 2 && context.getRowIndex() > 3) {
            String cellValue = context.getCell().getStringCellValue();
            if (cellValue != null  && cellValue.contains("经费") && cellValue.contains("拨款")) {
                WriteFont writeFont = new WriteFont();
                writeFont.setColor(IndexedColors.RED.getIndex());
                writeCellStyle.setWriteFont(writeFont);
            }
        }
        if (context.getRelativeRowIndex() == null || context.getRelativeRowIndex() <= 0) {
            WriteCellStyle.merge(contentWriteCellStyleList.get(0), cellData.getOrCreateStyle());
        } else {
            WriteCellStyle.merge(
                contentWriteCellStyleList.get(context.getRelativeRowIndex() % contentWriteCellStyleList.size()),
                cellData.getOrCreateStyle());
        }
        // context.getRow().setHeight(0);

        // 经费 拨款 红色
        // if (context.getColumnIndex() > 2 && context.getRowIndex() > 3) {
        //     String cellValue = context.getCell().getStringCellValue();
        //     if (cellValue != null  && cellValue.contains("经费") && cellValue.contains("拨款")) {
        //         CellStyle cellStyle = context.getWriteSheetHolder().getSheet().getWorkbook().createCellStyle();
        //         Font font = context.getWriteSheetHolder().getSheet().getWorkbook().createFont();
        //         font.setColor(IndexedColors.RED.getIndex());
        //         cellStyle.setFont(font);
        //         context.getCell().setCellStyle(cellStyle);
        //     }
        // }
    }

    protected boolean stopProcessing(CellWriteHandlerContext context) {
        return context.getFirstCellData() == null;
    }

}
