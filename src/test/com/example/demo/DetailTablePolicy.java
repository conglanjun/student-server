package com.example.demo;

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

public class DetailTablePolicy extends DynamicTableRenderPolicy {

    int tableColumnCount = 5;
    int tableDataRows = 2;

    @Override
    public void render(XWPFTable table, Object o) throws Exception {
        DetailData detailTable = (DetailData)o;
        List<DataBase> dataBaseList = detailTable.getDataBaseList();
        if (dataBaseList != null) {
            for (int i = dataBaseList.size() - 1; i >= 0; i--) {
                DataBase dataBase = dataBaseList.get(i);
                List<String> params = new ArrayList<>();
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(tableDataRows);
                for (int j = 0; j < tableColumnCount; j++) {
                    insertNewTableRow.createCell();
                }
                // parent project; project; mini project
                if (dataBase.getProjectParent().equals(dataBase.getProject())) {
                    params.add(dataBase.getProject());
                    if (dataBase.getMiniProject() == null || dataBase.getMiniProject().isEmpty()) {
                        TableTools.mergeCellsHorizonal(table, tableDataRows, 0, 2);
                    } else {
                        TableTools.mergeCellsHorizonal(table, tableDataRows, 0, 1);
                        params.add(dataBase.getMiniProject());
                    }
                } else {
                    params.add(dataBase.getProjectParent());
                    params.add(dataBase.getProject());
                    if (dataBase.getMiniProject() == null || dataBase.getMiniProject().isEmpty()) {
                        TableTools.mergeCellsHorizonal(table, tableDataRows, 1, 2);
                    } else {
                        params.add(dataBase.getMiniProject());
                    }
                }
                // single
                String singleResult = "";
                if (dataBase.getSingleItemScore() == 0) {
                    singleResult += "-";
                } else {
                    singleResult += dataBase.getSingleItemScore();
                }
                if (dataBase.getSingleItemRemark() == null || dataBase.getSingleItemRemark().isEmpty()) {
                    singleResult += "\n/";
                } else {
                    singleResult += "\n" + dataBase.getSingleItemRemark();
                }
                params.add(singleResult);

                // comprehensive
                String comprehensiveResult = "";
                if (dataBase.getComprehensiveScore() == 0) {
                    comprehensiveResult += "-";
                } else {
                    comprehensiveResult += dataBase.getComprehensiveScore();
                }
                if (dataBase.getComprehensiveRemark() == null || dataBase.getComprehensiveRemark().isEmpty()) {
                    comprehensiveResult += "\n/";
                } else {
                    comprehensiveResult += "\n" + dataBase.getComprehensiveRemark();
                }
                params.add(comprehensiveResult);
                RowRenderData rowRenderData = Rows.of(params.toArray(new String[0])).create();

                TableRenderPolicy.Helper.renderRow(table.getRow(tableDataRows), rowRenderData);
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
    }
}
