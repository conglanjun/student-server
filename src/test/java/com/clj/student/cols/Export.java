package com.clj.student.cols;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;


@SpringBootTest(classes = Export.class)
public class Export {
    
    FileData fileData = new FileData();
    DocumentData documentData = new DocumentData();

    @BeforeEach
    public void init() {
        // first
        List<DataBase> projectScoreList = new ArrayList<>();
        DataBase evaluation1 = new DataBase();
        evaluation1.setProjectParent("市容环卫");
        evaluation1.setProject("综合成绩");
        evaluation1.setScore("93.19");
        evaluation1.setRank("全市并列第二");
        DataBase evaluation2 = new DataBase();
        evaluation2.setProjectParent("市容环卫");
        evaluation2.setProject("道路清洗（扫）保洁");
        evaluation2.setScore("91.34");
        evaluation2.setRank("全市并列第二");
        // DataBase evaluation3 = new DataBase();
        // evaluation3.setProjectParent("市容环卫");
        // evaluation3.setProject("环卫公厕");
        // evaluation3.setScore("97");
        // evaluation3.setRank("排名未公布");
        // DataBase evaluation4 = new DataBase();
        // evaluation4.setProjectParent("市容环卫");
        // evaluation4.setProject("道班房");
        // evaluation4.setScore("95");
        // evaluation4.setRank("排名未公布");
        // DataBase evaluation5 = new DataBase();
        // evaluation5.setProjectParent("背街小巷保洁");
        // evaluation5.setProject("综合成绩");
        // evaluation5.setScore("98.49");
        // evaluation5.setRank("岛内第二");
        DataBase evaluation6 = new DataBase();
        evaluation6.setProjectParent("背街小巷保洁");
        evaluation6.setProject("红榜数");
        evaluation6.setScore("7"); // add 条
        evaluation6.setRank("排名未公布");
        DataBase evaluation7 = new DataBase();
        evaluation7.setProjectParent("背街小巷保洁");
        evaluation7.setProject("黑榜数");
        evaluation7.setRank("成绩未公布");
        DataBase evaluation8 = new DataBase();
        evaluation8.setProjectParent("园林绿化");
        evaluation8.setProject("公园绿地");
        // evaluation8.setScore("87.1");
        // evaluation8.setRank("全市第一");
        DataBase evaluation9 = new DataBase();
        evaluation9.setProjectParent("园林绿化");
        evaluation9.setProject("道路绿地");
        evaluation9.setScore("87.46");
        evaluation9.setRank("全市第一");

        projectScoreList.add(evaluation1);
        projectScoreList.add(evaluation2);
        // projectScoreList.add(evaluation3);
        // projectScoreList.add(evaluation4);
        // projectScoreList.add(evaluation5);
        projectScoreList.add(evaluation6);
        projectScoreList.add(evaluation7);
        projectScoreList.add(evaluation8);
        projectScoreList.add(evaluation9);

        Instance i1 = new Instance();
        i1.setName("湖里城建");
        i1.setCol1("成绩");
        i1.setCol2("排名");
        i1.setProjectScoreList(projectScoreList);

        // second
        List<DataBase> projectScoreListB = new ArrayList<>();
        DataBase evaluationB1 = new DataBase();
        evaluationB1.setProjectParent("市容环卫");
        evaluationB1.setProject("综合成绩");
        DataBase evaluationB2 = new DataBase();
        evaluationB2.setProjectParent("市容环卫");
        evaluationB2.setProject("道路清洗（扫）保洁");
        DataBase evaluationB3 = new DataBase();
        evaluationB3.setProjectParent("市容环卫");
        evaluationB3.setProject("环卫公厕");
        evaluationB3.setScore("96");
        evaluationB3.setRank("排名未公布");
        DataBase evaluationB4 = new DataBase();
        evaluationB4.setProjectParent("市容环卫");
        evaluationB4.setProject("道班房");
        DataBase evaluationB5 = new DataBase();
        evaluationB5.setProjectParent("背街小巷保洁");
        evaluationB5.setProject("综合成绩");
        evaluationB5.setScore("99.51");
        evaluationB5.setRank("高于");
        DataBase evaluationB6 = new DataBase();
        evaluationB6.setProjectParent("背街小巷保洁");
        evaluationB6.setProject("红榜数");
        evaluationB6.setScore("3"); // add 条
        evaluationB6.setRank("排名未公布");
        DataBase evaluationB7 = new DataBase();
        evaluationB7.setProjectParent("背街小巷保洁");
        evaluationB7.setProject("黑榜数");
        evaluationB7.setRank("成绩未公布");
        DataBase evaluationB8 = new DataBase();
        evaluationB8.setProjectParent("园林绿化");
        evaluationB8.setProject("道路绿地");
        // evaluationB8.setRank("成绩未公布");
        DataBase evaluationB9 = new DataBase();
        evaluationB9.setProjectParent("园林绿化");
        evaluationB9.setProject("公园绿地");
        evaluationB9.setRank("成绩未公布");

        projectScoreListB.add(evaluationB1);
        projectScoreListB.add(evaluationB2);
        projectScoreListB.add(evaluationB3);
        projectScoreListB.add(evaluationB4);
        projectScoreListB.add(evaluationB5);
        projectScoreListB.add(evaluationB6);
        projectScoreListB.add(evaluationB7);
        projectScoreListB.add(evaluationB8);
        projectScoreListB.add(evaluationB9);

        Instance i2 = new Instance();
        i2.setName("翔安城建");
        i2.setCol1("成绩");
        i2.setCol2("相较翔安区平均分");
        i2.setProjectScoreList(projectScoreListB);

        List<Instance> instances = new ArrayList<>();

        instances.add(i1);
        instances.add(i2);

        DetailData dd = new DetailData();
        dd.setDataBaseList(instances);

        Map<String, List<DataBase>> rowInstanceData = new TreeMap<>();
        List<Map<String, DataBase>> instanceMaps = new ArrayList<>(); 
        // construct key map
        for (int i = 0; i < instances.size(); i++) {
            Instance instance = instances.get(i);
            List<DataBase> scoreList = instance.getProjectScoreList();
            Map<String, DataBase> instanceMap = new HashMap<>();
            for (int j = 0; j < scoreList.size(); j++) {
                DataBase dataBase = scoreList.get(j);
                String key = dataBase.getProjectParent() + "-and-" + dataBase.getProject();
                instanceMap.put(key, dataBase);
                if (!rowInstanceData.containsKey(key)) {
                    rowInstanceData.put(key, new ArrayList<>());
                }
            }
            instanceMaps.add(instanceMap);
        }

        // fill data
        for(String key: rowInstanceData.keySet()) {
            for (int i = 0; i < instanceMaps.size(); i++) {
                Map<String, DataBase> dataBaseMap = instanceMaps.get(i);
                if (dataBaseMap.containsKey(key)) {
                    rowInstanceData.get(key).add(dataBaseMap.get(key));
                } else {
                    String[] ps = key.split("-and-");
                    DataBase dataBase = new DataBase();
                    dataBase.setProjectParent(ps[0]);
                    dataBase.setProject(ps[1]);
                    rowInstanceData.get(key).add(dataBase);
                }
            }
        }

        dd.setRowInstanceData(rowInstanceData);

        documentData.setDocument("2023年11月市容环卫、园林绿化专项考评情况");
        documentData.setComment("说明：11月市政设施专项考评湖里城建、翔安城建均未收到相关通报");
        documentData.setDetailTable(dd);

        fileData.setDocumentData(documentData);
    }
    
    @Test
    public void export() throws IOException {
        Configure config = Configure.builder().bind("document_data", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("src/test/resources/template_env.docx", config).render(fileData);
        template.writeToFile("out_example_env.docx");
    }

}
