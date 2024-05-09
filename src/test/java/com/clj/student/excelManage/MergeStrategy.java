package com.clj.student.excelManage;

import static org.mockito.ArgumentMatchers.booleanThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;

public class MergeStrategy extends AbstractMergeStrategy {

    private Integer lastRowIndex;
    private Integer quarterIndex;

    public void setLastRowIndex(int lastRowIndex) {
        this.lastRowIndex = lastRowIndex;
    }

    public int getLastRowIndex() {
        return lastRowIndex;
    }

    public void setQuarterIndex(int quarterIndex) {
        this.quarterIndex = quarterIndex;
    }

    public int getQuarterIndex() {
        return quarterIndex;
    }

    public MergeStrategy(int lastRowIndex, int quarterIndex) {
        this.lastRowIndex = lastRowIndex;
        this.quarterIndex = quarterIndex;
    }

    Map<String, List<Integer>> col0 = new HashMap<>();
    Map<String, List<Integer>> col1 = new HashMap<>();

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
        if (cell.getColumnIndex() == 0) {
            mergeHandle(sheet, cell, col0, 0);
        }
        if (cell.getColumnIndex() == 1) {
            mergeHandle(sheet, cell, col1, 1);
        }
        if (cell.getColumnIndex() > 2 && cell.getColumnIndex() < 15 && cell.getRowIndex() == quarterIndex) {
            boolean existed = false;
            for (CellRangeAddress cra: sheet.getMergedRegions()) {
                if (cra.containsRow(quarterIndex) && cra.containsColumn(cell.getColumnIndex())) {
                    existed = true;
                    break;
                }
            }
            if (!existed) {
                int index = cell.getColumnIndex();
                sheet.addMergedRegion(new CellRangeAddress(quarterIndex, quarterIndex, index, index + 2));
            }
        }
    }

    public void mergeHandle(Sheet sheet, Cell cell, Map<String, List<Integer>> col, int colIndex) {
        Cell prCell = sheet.getRow(cell.getRowIndex() - 1).getCell(cell.getColumnIndex());
        int firstRow = prCell.getRowIndex();
        int lastRow = cell.getRowIndex();
        if (prCell.getStringCellValue().equals(cell.getStringCellValue())) {
            List<Integer> indexes = new ArrayList<>();
            if (col0.containsKey(prCell.getStringCellValue())) {
                indexes.add(col0.get(prCell.getStringCellValue()).get(0));
            } else {
                indexes.add(firstRow);
            }
            indexes.add(lastRow);
            col0.put(prCell.getStringCellValue(), indexes);
        } else {
            if (col0.containsKey(prCell.getStringCellValue())) {
                sheet.addMergedRegion(new CellRangeAddress(col0.get(prCell.getStringCellValue()).get(0), col0.get(prCell.getStringCellValue()).get(1), colIndex, colIndex));
                col0.remove(prCell.getStringCellValue());
            }
        }
        int currentRowIndex = cell.getRowIndex();
        if (currentRowIndex == this.lastRowIndex) {
            if (col0.containsKey(prCell.getStringCellValue())) {
                sheet.addMergedRegion(new CellRangeAddress(col0.get(prCell.getStringCellValue()).get(0), col0.get(prCell.getStringCellValue()).get(1), colIndex, colIndex));
                col0.remove(prCell.getStringCellValue());
            }
        }
    }

    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        if (context.getHead() && context.getRowIndex() == 1) { // 判断是否是表头行的第二行
            if ("单位".equals(context.getCell().getStringCellValue())) { // 判断是否为单位字段
                // 对应的单元格与下一个单元格合并
                Sheet sheet = context.getWriteSheetHolder().getSheet();
                sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 1));
            }
        }
        if (context.getRowIndex() > 4) {
            merge(context.getWriteSheetHolder().getSheet(), context.getCell(), context.getHeadData(),
            context.getRelativeRowIndex());
        }
    }
    
}
