package com.clj.student.demo;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = ExportTestV2.class)
public class ExportTestV2 {

    DocumentData documentData = new DocumentData();

    String data = "[{\"id\":null,\"projectParent\":\"1.平台考核【20%】\",\"project\":\"1.平台考核【20%】\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"91\",\"singleItemRanking\":\"全市并列第一\",\"projectParent\":\"1.平台考核【20%】\",\"project\":\"PTKH_ZHCJ\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"89\",\"singleItemRanking\":\"全市第二\",\"projectParent\":\"2.城市精细化管理【60%】\",\"project\":\"社区网格管理\\n（30%）\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"94\",\"singleItemRanking\":\"全市第一\",\"projectParent\":\"2.城市精细化管理【60%】\",\"project\":\"主要道路管理（15%）\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"94.07\",\"singleItemRanking\":\"全市第一\",\"projectParent\":\"2.城市精细化管理【60%】\",\"project\":\"重点要素管理（15%）\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"91.52\",\"singleItemRanking\":\"全市第一\",\"projectParent\":\"2.城市精细化管理【60%】\",\"project\":\"CSJXHGL_ZHCJ\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"12\",\"singleItemRanking\":\"1\",\"projectParent\":\"3.督察检查【10%】\",\"project\":\"3.督察检查【10%】\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"90\",\"singleItemRanking\":\"全市第五\",\"projectParent\":\"3.督察检查【10%】\",\"project\":\"DCJC_ZHCJ\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"45.67\",\"singleItemRanking\":\"全市并列第二\",\"projectParent\":\"4.行业考评【10%】\",\"project\":\"市容环卫（2.5%）\",\"minProject\":\"道路清洗（扫）保洁\"},{\"id\":null,\"singleItemScore\":\"33.87\",\"singleItemRanking\":\"全市并列第四\",\"projectParent\":\"4.行业考评【10%】\",\"project\":\"市容环卫（2.5%）\",\"minProject\":\"环卫设施\"},{\"id\":null,\"singleItemScore\":\"13.65\",\"singleItemRanking\":\"全市并列第一\",\"projectParent\":\"4.行业考评【10%】\",\"project\":\"市容环卫（2.5%）\",\"minProject\":\"组织管理\"},{\"id\":null,\"singleItemScore\":\"93.19\",\"singleItemRanking\":\"全市并列第二\",\"projectParent\":\"4.行业考评【10%】\",\"project\":\"市容环卫（2.5%）\",\"minProject\":\"SRHW_ZHCJ\"},{\"id\":null,\"singleItemScore\":\"81.12\",\"singleItemRanking\":\"全市并列第一\",\"projectParent\":\"4.行业考评【10%】\",\"project\":\"扬尘防治（2.5%）\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"\",\"singleItemRanking\":\"\",\"projectParent\":\"4.行业考评【10%】\",\"project\":\"门前三包”管理（2.5%）\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"12\",\"singleItemRanking\":\"1\",\"projectParent\":\"4.行业考评【10%】\",\"project\":\"两违”综合治理（2.5%）\",\"minProject\":\"\"},{\"id\":null,\"singleItemScore\":\"85.28\",\"singleItemRanking\":\"全市并列第二\",\"projectParent\":\"4.行业考评【10%】\",\"project\":\"HYKP_ZHCJ\",\"minProject\":\"\"}]";

    public DataBaseV2 constructDataBaseV2(Map<String, Object> obj) {
        DataBaseV2 data1 = new DataBaseV2();
        data1.setProjectParent((String) obj.get("projectParent"));
        data1.setProject((String) obj.get("project"));
        data1.setMinProject((String) obj.get("minProject"));
        if (obj.get("singleItemScore") != null && !((String) obj.get("singleItemScore")).isEmpty()) {
            float score = Float.parseFloat((String) obj.get("singleItemScore"));
            data1.setSingleItemScore(score);
        }
        if (obj.get("singleItemRanking") != null && !((String) obj.get("singleItemRanking")).isEmpty()) {
            data1.setSingleItemRanking((String) obj.get("singleItemRanking"));
        }
        return data1;
    }

    @BeforeEach
    public void init() {
        JSONParser parser = new JSONParser(data);

        List<DataBaseV2> dataBaseList = new ArrayList<>();
        Map<String, DataBaseV2> comprehensiveMap = new HashMap<>();
        try {
            ArrayList<Object> array = parser.parseArray();
            // ZHCJ
            for (int i = 0; i < array.size(); i++) {
                Map<String, Object> obj = (Map<String, Object>) array.get(i);
                DataBaseV2 data1 = constructDataBaseV2(obj);
                if (data1.getProject() != null && data1.getProject().contains("_ZHCJ")) {
                    data1.setComprehensiveProject("project");
                    comprehensiveMap.put(data1.getProjectParent(), data1);
                } else if (data1.getMinProject() != null && data1.getMinProject().contains("_ZHCJ")) {
                    data1.setComprehensiveProject("minProject");
                    comprehensiveMap.put(data1.getProject(), data1);
                }
            }
            for (int i = 0; i < array.size(); i++) {
                Map<String, Object> obj = (Map<String, Object>) array.get(i);
                DataBaseV2 data1 = constructDataBaseV2(obj);
                DataBaseV2 dataBaseV2 = null;
                if (data1.getProject() != null && !(data1.getProject().contains("_ZHCJ") || data1.getMinProject().contains("_ZHCJ"))) {
                    if (!data1.getMinProject().isEmpty() && !data1.getMinProject().contains("_ZHCJ")) {
                        // project
                        String project = data1.getProject();
                        dataBaseV2 = comprehensiveMap.get(project);
                        if (dataBaseV2 == null) {
                            continue;
                        }
                    } else if (!data1.getProject().isEmpty() && !data1.getProject().contains("_ZHCJ")) {
                        // project parent
                        String projectParent = data1.getProjectParent();
                        dataBaseV2 = comprehensiveMap.get(projectParent);
                        if (dataBaseV2 == null) {
                            continue;
                        }
                    }
                    // assign merge start end
                    if (dataBaseV2 == null) {
                        continue;
                    }
                    if (dataBaseV2.getComprehensiveMergeStart() == null && dataBaseV2.getComprehensiveMergeEnd() == null) {
                        dataBaseV2.setComprehensiveMergeStart(String.valueOf(dataBaseList.size()));
                    }
                    dataBaseV2.setComprehensiveMergeEnd(String.valueOf(dataBaseList.size()));
                    data1.setComprehensiveScore(dataBaseV2.getSingleItemScore());
                    data1.setComprehensiveRanking(dataBaseV2.getSingleItemRanking());
                    dataBaseList.add(data1);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        documentData.setDocument("2023年11月份全市创建全国文明城市工作暨城市综合管理考评情况");
        DetailData dd = new DetailData();
        dd.setComment("备注测试内容");
        dd.setComprehensiveMap(comprehensiveMap);
        dd.setDataBaseList(dataBaseList);
        documentData.setDetailTable(dd);

    }

    @Test
    public void exportV2() throws IOException {
        Configure config = Configure.builder().bind("detail_table", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("src/test/resources/template_v2.docx", config).render(documentData);
        template.writeToFile("out_example_v2.docx");
    }
}
