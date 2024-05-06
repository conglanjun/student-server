package com.clj.student.demo;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = ExportTest.class)
public class ExportTest {

    DocumentData documentData = new DocumentData();


    @BeforeEach
    public void init() {
        List<DataBase> dataBaseList = new ArrayList<>();
        DataBase data1 = new DataBase();
        data1.setProjectParent("1.平台考核【20%】");
        data1.setProject("1.平台考核【20%】");
        data1.setComprehensiveScore(91);
        data1.setComprehensiveRemark("全市并列第一");
        DataBase data2 = new DataBase();
        data2.setProjectParent("2.城市精细化管理【60%】");
        data2.setProject("社区网格管理\n" +
                "（30%）");
        data2.setSingleItemScore(89);
        data2.setSingleItemRemark("全市第二");
        data2.setComprehensiveScore(91.52f);
        data2.setComprehensiveRemark("全市第一");

        DataBase data3 = new DataBase();
        data3.setProjectParent("2.城市精细化管理【60%】");
        data3.setProject("主要道路管理（15%）");
        data3.setSingleItemScore(94);
        data3.setSingleItemRemark("全市第一");
        data3.setComprehensiveScore(91.52f);
        data3.setComprehensiveRemark("全市第一");

        DataBase data4 = new DataBase();
        data4.setProjectParent("2.城市精细化管理【60%】");
        data4.setProject("重点要素管理（15%）");
        data4.setSingleItemScore(94.07f);
        data4.setSingleItemRemark("全市第一");
        data4.setComprehensiveScore(91.52f);
        data4.setComprehensiveRemark("全市第一");

        DataBase data5 = new DataBase();
        data5.setProjectParent("3.督察检查【10%】");
        data5.setProject("3.督察检查【10%】");
        data5.setComprehensiveScore(90);
        data5.setComprehensiveRemark("全市第五");

        DataBase data6 = new DataBase();
        data6.setProjectParent("4.行业考评【10%】");
        data6.setProject("市容环卫（2.5%）");
        data6.setMiniProject("道路清洗（扫）保洁");
        data6.setSingleItemScore(45.67f);
        data6.setSingleItemRemark("全市并列第二");
        data6.setComprehensiveScore(93.19f);
        data6.setComprehensiveRemark("全市并列第二");

        DataBase data7 = new DataBase();
        data7.setProjectParent("4.行业考评【10%】");
        data7.setProject("市容环卫（2.5%）");
        data7.setMiniProject("环卫设施");
        data7.setSingleItemScore(33.87f);
        data7.setSingleItemRemark("全市并列第四");
        data7.setComprehensiveScore(93.19f);
        data7.setComprehensiveRemark("全市并列第二");

        DataBase data8 = new DataBase();
        data8.setProjectParent("4.行业考评【10%】");
        data8.setProject("市容环卫（2.5%）");
        data8.setMiniProject("组织管理");
        data8.setSingleItemScore(13.65f);
        data8.setSingleItemRemark("全市并列第一");
        data8.setComprehensiveScore(93.19f);
        data8.setComprehensiveRemark("全市并列第二");

        DataBase data9 = new DataBase();
        data9.setProjectParent("4.行业考评【10%】");
        data9.setProject("扬尘防治（2.5%）");
        data9.setComprehensiveScore(85.84f);
        data9.setComprehensiveRemark("全市并列第一");

        DataBase data10 = new DataBase();
        data10.setProjectParent("4.行业考评【10%】");
        data10.setProject("“门前三包”管理（2.5%）");
        data10.setComprehensiveScore(85.8f);
        data10.setComprehensiveRemark("全市并列第四");

        DataBase data11 = new DataBase();
        data11.setProjectParent("4.行业考评【10%】");
        data11.setProject("“两违”综合治理（2.5%）");
        data11.setComprehensiveScore(85.28f);
        data11.setComprehensiveRemark("全市并列第二");

        dataBaseList.add(data1);
        dataBaseList.add(data2);
        dataBaseList.add(data3);
        dataBaseList.add(data4);
        dataBaseList.add(data5);
        dataBaseList.add(data6);
        dataBaseList.add(data7);
        dataBaseList.add(data8);
        dataBaseList.add(data9);
        dataBaseList.add(data10);
        dataBaseList.add(data11);

        documentData.setDocument("2023年11月份全市创建全国文明城市工作暨城市综合管理考评情况");
        DetailData dd = new DetailData();
        // dd.setDataBaseList(dataBaseList);
        documentData.setDetailTable(dd);

    }

    @Test
    public void export() throws IOException {
        Configure config = Configure.builder().bind("detail_table", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("src/test/resources/template.docx", config).render(documentData);
        template.writeToFile("out_example.docx");
    }
}
