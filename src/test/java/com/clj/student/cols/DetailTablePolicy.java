package com.clj.student.cols;

import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.style.TableStyle;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.util.TableTools;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class DetailTablePolicy extends DynamicTableRenderPolicy {

    int instanceRow = 0;
    int tableCol = 1;

    @Override
    public void render(XWPFTable table, Object model) {
        if (null == model) {
            return;
        }

        if (!(model instanceof TableRenderData)) {
            // throw new RenderPolicyException("数据类型错误，期待TableRenderData，实际" + model.getClass());
        }

        DocumentData documentData = (DocumentData)model;
        List<Instance> instances = documentData.getDetailTable().getDataBaseList();
        Map<String, List<DataBase>> rowInstanceData = documentData.getDetailTable().getRowInstanceData();

        XWPFDocument doc = table.getBody().getXWPFDocument();
        doc.removeBodyElement(doc.getPosOfTable(table));

         // 添加标题内容
        XWPFParagraph titleParagraph = doc.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setBold(true);
        titleRun.setFontFamily("创艺简标宋");
        titleRun.setFontSize(20);
        titleRun.setText(documentData.getDocument());

        int cols = 2 + instances.size() * 2;
        for (int i = 0; i < instances.size(); i++) {
            Instance instance = instances.get(i);
            if ("步道公司".equals(instance.getName())) {
                cols -= 1;
            }
        }

        XWPFTable newTable = doc.createTable(3, cols);
        // merge 1 row and 1, 2 cells 
        newTable.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
        newTable.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
        // 2 row
        newTable.getRow(1).getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
        newTable.getRow(1).getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);

        TableTools.mergeCellsVertically(newTable, 0, 0, 1);
        newTable.getRow(0).getCell(0).setText("考评专项");
        // header style
        // XWPFParagraph headerParagraph = newTable.getRow(0).getCell(0).getParagraphs().get(0);
        // XWPFRun headerRun = headerParagraph.createRun();
        // headerRun.setFontFamily("仿宋_GB2312");
        // headerRun.setFontSize(14);
        for (int i = 0; i < instances.size(); i++) {
            Instance instance = instances.get(i);
            newTable.getRow(0).getCell(2 + i * 2).setText(instance.getName());
            // merge instance name
            newTable.getRow(0).getCell(2 + i * 2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            if (!"步道公司".equals(instance.getName())) {
                newTable.getRow(0).getCell(2 + i * 2 + 1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
            // col conetent
            newTable.getRow(1).getCell(2 + i * 2).setText(instance.getCol1());
            if (!"步道公司".equals(instance.getName())) {
                newTable.getRow(1).getCell(2 + i * 2 + 1).setText(instance.getCol2());
            }
        }


        int rowIndex = 1;
        for (String key: rowInstanceData.keySet()) {
            rowIndex++;
            List<DataBase> dataBases = rowInstanceData.get(key);
            XWPFTableRow row = newTable.getRow(rowIndex);
            if (row == null) {
                row = newTable.createRow();
            }
            for (int i = 0; i < dataBases.size(); i++) {
                DataBase dataBase = dataBases.get(i);
                // parent project and project
                if (i == 0) {
                    row.getCell(0).setText(dataBase.getProjectParent());
                    row.getCell(1).setText(dataBase.getProject());
                }
                // 成绩未公布 merge
                if (dataBase.getRank() != null && dataBase.getRank().equals("成绩未公布")) {
                    row.getCell(2 + i * 2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                    row.getCell(2 + i * 2 + 1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);

                    row.getCell(2 + i * 2).setText(dataBase.getRank());
                } else {
                    // instance1 score
                    if (dataBase.getScore() == null || dataBase.getScore().isEmpty() || "null".equals(dataBase.getScore())) {
                        row.getCell(2 + i * 2).setText("/");
                    } else {
                        if ("红榜数".equals(dataBase.getProject()) || "黑榜数".equals(dataBase.getProject())) {
                            row.getCell(2 + i * 2).setText(String.valueOf(String.valueOf(Integer.valueOf(dataBase.getScore())) + "条"));
                        } else {
                            row.getCell(2 + i * 2).setText(dataBase.getScore());
                        }
                    }
                    // instance1 rank
                    if (row.getCell(2 + i * 2 + 1) == null) {
                        continue;
                    }
                    if (dataBase.getRank() == null || dataBase.getRank().isEmpty() || "null".equals(dataBase.getScore())) {
                        row.getCell(2 + i * 2 + 1).setText("/");
                    } else {
                        row.getCell(2 + i * 2 + 1).setText(dataBase.getRank());
                    }
                }
            }
            
        }

        String memory = "";
        int columnMergeStart = 2;
        int columnMergeEnd = 2;
        for (int i = 0; i < newTable.getRows().size(); i++) {
            for (int j = 0; j < newTable.getRow(i).getTableCells().size(); j++) {
                CTTcPr tcPr = newTable.getRow(i).getCell(j).getCTTc().addNewTcPr();
                CTTblWidth tblWidth = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
                tblWidth.setType(STTblWidth.DXA);
                tblWidth.setW(BigInteger.valueOf(8000)); // 设置宽度为8000单位，根据需要调整
            }
        }
        int rowMergeIndex = 1;
        for (String key: rowInstanceData.keySet()) {
            rowMergeIndex++;
            List<DataBase> dataBases = rowInstanceData.get(key);
            if (dataBases.size() == 0) {
                continue;
            }
            // merge parent project
            if (memory.equals(dataBases.get(0).getProjectParent())) {
                columnMergeEnd++;
            } else {
                memory = dataBases.get(0).getProjectParent();
                // merge column
                if (columnMergeEnd > columnMergeStart) {
                    TableTools.mergeCellsVertically(newTable, 0, columnMergeStart, columnMergeEnd);
                }
                // reset start and end.
                columnMergeStart = rowMergeIndex;
                columnMergeEnd = columnMergeStart;
            }
        }
        if (columnMergeEnd > columnMergeStart) {
            TableTools.mergeCellsVertically(newTable, 0, columnMergeStart, columnMergeEnd);
        }
        // 添加备注说明
        XWPFParagraph commentParagraph = doc.createParagraph();
        XWPFRun commentRun = commentParagraph.createRun();
        commentRun.setFontSize(12);
        commentRun.setText(documentData.getComment());

        // style
        for(int j = 0; j < newTable.getRows().size(); j++) {
            XWPFTableRow row = newTable.getRows().get(j);
            for (int i = 0; i < row.getTableCells().size(); i++) {
                XWPFTableCell cell = row.getCell(i);
                if (j == 0) {
                    if (i == 0) {
                        cell.setWidth("136"); // 136 + 85
                    } else if (i == 1) {
                        cell.setWidth("85");
                    } else if (i == 2) {
                        cell.setWidth("78");
                    } else if (i == 3) {
                        if (instances.size() == 2) {
                            cell.setWidth("110"); // 188 - 78
                        } else if (instances.size() == 3) {
                            cell.setWidth("78"); // 156 - 78
                        }
                    } else if (i == 4) {
                        if (instances.size() == 2) {
                            cell.setWidth("80");
                        } else if (instances.size() == 3) {
                            cell.setWidth("78"); // 156 - 78
                        }
                    }  else if (i == 5) {
                        if (instances.size() == 3) {
                            cell.setWidth("78"); // 156 - 78
                        }
                    } else if (i == 6) {
                        cell.setWidth("80");
                    }
                }
                cell.setVerticalAlignment(XWPFVertAlign.CENTER);
                XWPFParagraph cellPara = cell.getParagraphs().get(0);
                cellPara.setAlignment(ParagraphAlignment.CENTER);

                CTP ctp = cell.getCTTc().sizeOfPArray() == 0? cell.getCTTc().addNewP() : cell.getCTTc().getPArray(0);
                XWPFParagraph para = cell.getParagraph(ctp);
                XWPFRun run = para.getRuns().size() == 0 ? para.createRun() : para.getRuns().get(0);
                run.setFontFamily("仿宋_GB2312");
                if (j < 2) {
                    run.setFontSize(15);
                } else {
                    
                }
            }

            CTTrPr trPr = row.getCtRow().addNewTrPr();
            CTHeight ht = trPr.addNewTrHeight();
            if (j == 0) {
                if (instances.size() == 2) {
                    ht.setVal(BigInteger.valueOf(76));
                } else {
                    ht.setVal(BigInteger.valueOf(78));
                }
            } else if (j == 1) {
                ht.setVal(BigInteger.valueOf(48));
            } else {
                ht.setVal(BigInteger.valueOf(42));
            }
            
        }

    }

}
