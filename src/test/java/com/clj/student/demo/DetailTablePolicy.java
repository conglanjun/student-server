package com.clj.student.demo;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailTablePolicy extends DynamicTableRenderPolicy {

    int tableColumnCount = 6;
    int tableDataRows = 2;

    @Override
    public void render(XWPFTable table, Object o) throws Exception {
        XWPFDocument doc = table.getBody().getXWPFDocument();
        // 添加标题内容
        if (doc.getParagraphs().size() > 0) {
            XWPFParagraph titleParagraph = doc.getParagraphs().get(0);
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = titleParagraph.getRuns().size() == 0 ? titleParagraph.createRun() : titleParagraph.getRuns().get(0);
            titleRun.setBold(true);
            titleRun.setFontFamily("创艺简标宋");
            titleRun.setFontSize(20);
        }

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

        // table.setWidth("514");
        // style
        int maxColSize = 0;
        for(int j = 0; j < table.getRows().size(); j++) {
            XWPFTableRow row = table.getRows().get(j);
            if (maxColSize < row.getTableCells().size()) {
                maxColSize = row.getTableCells().size();
            }
        }

        for(int j = 0; j < table.getRows().size(); j++) {
            XWPFTableRow row = table.getRows().get(j);
            for (int i = 0; i < row.getTableCells().size(); i++) {
                XWPFTableCell cell = row.getCell(i);
                if (j == 0) { // first row
                    if (i == 0) { // first column
                        // cell.setWidth("182"); // 182
                    } else if (i == 1) { // second column
                        // cell.setWidth("332");
                    }
                } else if (j == 1) { // second row
                    // if (i == 0) { // first column
                    //     cell.setWidth("182"); // 182
                    // } else if (i == 1) { // second column
                    //     cell.setWidth("192");
                    // } else if (i == 2) { // third column
                    //     cell.setWidth("140");
                    // } else if (i == 3) { // comment
                    //     // cell.setWidth("140");
                    // }
                } else if (j > 1) {
                    // int cellSize = row.getTableCells().size();
                    if (maxColSize == 4) {
                        if (i == 0) {
                            cell.setWidth("182");
                        } else if (i == 1) { // second column
                            cell.setWidth("192");
                        } else if (i == 2) { // third column
                            cell.setWidth("140");
                        } else if (i == 3) { // comment
                            // cell.setWidth("140");
                        }
                    } else if (maxColSize == 5) {
                        if (i == 0) {
                            cell.setWidth("60");
                        } else if (i == 1) {
                            cell.setWidth("122");
                        } else if (i == 2) { // second column
                            cell.setWidth("192");
                        } else if (i == 3) { // third column
                            cell.setWidth("140");
                        } else if (i == 4) { // comment
                            // cell.setWidth("140");
                        }
                    } else if (maxColSize == 6) {
                        if (i == 0) {
                            cell.setWidth("60");
                        } else if (i == 1) {
                            cell.setWidth("60");
                        } else if (i == 2) {
                            cell.setWidth("62");
                        } else if (i == 3) { // second column
                            cell.setWidth("192");
                        } else if (i == 4) { // third column
                            cell.setWidth("140");
                        } else if (i == 5) { // comment
                            // cell.setWidth("140");
                        }
                    }
                }
                cell.setVerticalAlignment(XWPFVertAlign.CENTER);
                List<XWPFParagraph>  paraps = cell.getParagraphs();
                for (int i1 = 0; i1 < paraps.size(); i1++) {
                    XWPFParagraph xwpfParagraph = paraps.get(i1);
                    xwpfParagraph.setAlignment(ParagraphAlignment.CENTER);
                    List<XWPFRun> runs = xwpfParagraph.getRuns();
                    for (int k = 0; k < runs.size(); k++) {
                        XWPFRun xwpfRun = runs.get(k);
                        // CTFonts font = xwpfRun.getCTR().addNewRPr().addNewRFonts();
                        // //中文
                        // font.setEastAsia("仿宋_GB2312"); //改变中文字体设置这个
                        // // ASCII
                        // font.setAscii("Times New Roman"); //改变数字或者英文字体需要设置这个

                        xwpfRun.setFontFamily("仿宋_GB2312");
                        xwpfRun.setFontSize(12);
                        
                    }
                }
            }

            CTTrPr trPr = row.getCtRow().addNewTrPr();
            CTHeight ht = trPr.addNewTrHeight();
            if (j == 0) {
                ht.setVal(BigInteger.valueOf(74));
            } else if (j == 1) {
                ht.setVal(BigInteger.valueOf(48));
            } else {
                ht.setVal(BigInteger.valueOf(50));
            }
            
        }
    }
}
