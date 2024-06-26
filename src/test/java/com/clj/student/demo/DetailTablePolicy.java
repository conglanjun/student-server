package com.clj.student.demo;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailTablePolicy extends DynamicTableRenderPolicy {

    int tableColumnCount = 6;
    int tableDataRows = 2;

    @Override
    public void render(XWPFTable table, Object o) throws Exception {
        DetailData detailTable = (DetailData)o;
        List<DataBaseV2> dataBaseListOrigin = detailTable.getDataBaseList();
        List<DataBaseV2> dataBaseList = new ArrayList<>();
        // filter
        for (int i = 0; i < dataBaseListOrigin.size(); i++) {
            DataBaseV2 dataBaseV2 = dataBaseListOrigin.get(i);
            if (dataBaseV2.getSingleItemScore() == 0 && (dataBaseV2.getSingleItemRanking() == null || dataBaseV2.getSingleItemRanking().isEmpty()) && 
            dataBaseV2.getComprehensiveScore() == 0 && (dataBaseV2.getComprehensiveRanking() == null || dataBaseV2.getComprehensiveRanking().isEmpty())) {
                continue;
            }
            dataBaseList.add(dataBaseV2);
        }
        String comment = detailTable.getComment();
        if (dataBaseList != null) {
            int rowIndex = 0;
            for (int i = dataBaseList.size() - 1; i >= 0; i--) {
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(tableDataRows);
                for (int j = 0; j < tableColumnCount; j++) {
                    insertNewTableRow.createCell();
                }
                rowIndex++;
            }
            if (comment != null && rowIndex > 0) {
                TableTools.mergeCellsVertically(table, tableColumnCount - 1,  tableDataRows,  tableDataRows + rowIndex);
            }
            for (int i = dataBaseList.size() - 1; i >= 0; i--) {
                DataBaseV2 dataBase = dataBaseList.get(i);
                List<String> params = new ArrayList<>();
                // XWPFTableRow insertNewTableRow = table.insertNewTableRow(tableDataRows);
                // for (int j = 0; j < tableColumnCount; j++) {
                //     insertNewTableRow.createCell();
                // }
                // parent project; project; mini project
                if (dataBase.getProjectParent().equals(dataBase.getProject())) {
                    params.add(dataBase.getProject());
                    if (dataBase.getMinProject() == null || dataBase.getMinProject().isEmpty()) {
                        TableTools.mergeCellsHorizonal(table, tableDataRows + i, 0, 2);
                    } else {
                        TableTools.mergeCellsHorizonal(table, tableDataRows + i, 0, 1);
                        params.add(dataBase.getMinProject());
                    }
                } else {
                    params.add(dataBase.getProjectParent());
                    params.add(dataBase.getProject());
                    if (dataBase.getMinProject() == null || dataBase.getMinProject().isEmpty()) {
                        TableTools.mergeCellsHorizonal(table, tableDataRows + i, 1, 2);
                    } else {
                        params.add(dataBase.getMinProject());
                    }
                }
                // single
                String singleResult = "";
                if (dataBase.getSingleItemScore() == 0) {
                    singleResult += "-";
                } else {
                    singleResult += dataBase.getSingleItemScore();
                }
                if (dataBase.getSingleItemRanking() == null || dataBase.getSingleItemRanking().isEmpty()) {
                    singleResult += "\n/";
                } else {
                    singleResult += "\n" + dataBase.getSingleItemRanking();
                }
                params.add(singleResult);

                // comprehensive
                String comprehensiveResult = "";
                if (dataBase.getComprehensiveScore() == 0) {
                    comprehensiveResult += "-";
                } else {
                    comprehensiveResult += dataBase.getComprehensiveScore();
                }
                if (dataBase.getComprehensiveRanking() == null || dataBase.getComprehensiveRanking().isEmpty()) {
                    comprehensiveResult += "\n/";
                } else {
                    comprehensiveResult += "\n" + dataBase.getComprehensiveRanking();
                }
                params.add(comprehensiveResult);
                if (comment != null) {
                    params.add(comment);
                }
                RowRenderData rowRenderData = Rows.of(params.toArray(new String[0])).create();

                TableRenderPolicy.Helper.renderRow(table.getRow(tableDataRows + i), rowRenderData);
            }
            // merge column, the first column
            String memory = "";
            int columnMergeStart = 0;
            int columnMergeEnd = 0;
            String secondMemory = "";
            int columnSecondMergeStart = 0;
            int columnSecondMergeEnd = 0;
            for (int i = tableDataRows; i < table.getRows().size(); i++) {
                XWPFTableRow row = table.getRow(i);
                XWPFTableCell cell = row.getCell(0);
                XWPFTableCell secondCell = row.getCell(1);
                if (cell.getText() == null || cell.getText().isEmpty()) {
                    continue;
                }
                if (memory.equals(cell.getText())) {
                    columnMergeEnd++;
                } else {
                    memory = cell.getText();
                    // merge column
                    if (columnMergeEnd > columnMergeStart) {
                        TableTools.mergeCellsVertically(table, 0, columnMergeStart, columnMergeEnd);
                    }
                    // reset start and end.
                    columnMergeStart = i;
                    columnMergeEnd = columnMergeStart;
                }
                // second
                if (memory.equals(cell.getText()) && secondMemory.equals(secondCell.getText())) {
                    columnSecondMergeEnd++;
                } else {
                    secondMemory = secondCell.getText();
                    if (columnSecondMergeEnd > columnSecondMergeStart) {
                        TableTools.mergeCellsVertically(table, 1, columnSecondMergeStart, columnSecondMergeEnd);
                    }
                    // reset
                    columnSecondMergeStart = i;
                    columnSecondMergeEnd = columnSecondMergeStart;
                }
            }
            if (columnMergeEnd > columnMergeStart) {
                TableTools.mergeCellsVertically(table, 0, columnMergeStart, columnMergeEnd);
            }
            if (columnSecondMergeEnd > columnSecondMergeStart) {
                TableTools.mergeCellsVertically(table, 1, columnSecondMergeStart, columnSecondMergeEnd);
            }
            table.removeRow(table.getRows().size() - 1);
        }

        // merge comprehensive score and rank
        Map<String, DataBaseV2> comprehensiveMap = detailTable.getComprehensiveMap();
        if (comprehensiveMap != null) {
            for(String key: comprehensiveMap.keySet()) {
                DataBaseV2 dataBaseV2 = comprehensiveMap.get(key);
                int start = Integer.parseInt(dataBaseV2.getComprehensiveMergeStart());
                int end = Integer.parseInt(dataBaseV2.getComprehensiveMergeEnd());
                if (start >= end) {
                    continue;
                }
                TableTools.mergeCellsVertically(table, table.getRow(start + tableDataRows).getTableCells().size() - 2, start + tableDataRows, end + tableDataRows);
            }
        }
    }
}
