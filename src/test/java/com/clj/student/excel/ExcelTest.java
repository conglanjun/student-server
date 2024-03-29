package com.clj.student.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;

import lombok.Data;

@SpringBootTest(classes = ExcelTest.class)
public class ExcelTest {
    ScoreLevelExcelData scoreLevelExcelData = new ScoreLevelExcelData();

    @BeforeEach
    public void init() {

        Map<Integer, List<DataEntity>> dataMap = new HashMap<>();

        // January
        List<DataEntity> dataListJanuary = new ArrayList<>();
        // DataEntity d11 = new DataEntity();
        // d11.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d11.setMonth(Month.JANUARY);
        // d11.setCompanyName("湖里城建");
        // d11.setParentProject("步道公司");
        // d11.setProject("仙岳公园");
        // d11.setScore("97");
        // d11.setLevel("第一档");
        // DataEntity d12 = new DataEntity();
        // d12.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d12.setMonth(Month.JANUARY);
        // d12.setCompanyName("湖里城建");
        // d12.setParentProject("环卫运营中心");
        // d12.setProject("公厕");
        // d12.setScore("96");
        // d12.setLevel("第二档");
        // DataEntity d13 = new DataEntity();
        // d13.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d13.setMonth(Month.JANUARY);
        // d13.setCompanyName("湖里城建");
        // d13.setParentProject("环卫运营中心");
        // d13.setProject("道路保洁");
        // d13.setScore("91");
        // d13.setLevel("第三档");
        // DataEntity d14 = new DataEntity();
        // d14.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d14.setMonth(Month.JANUARY);
        // d14.setCompanyName("湖里城建");
        // d14.setParentProject("园林绿化运营中心");
        // d14.setProject("公园中心");
        // d14.setScore("90");
        // d14.setLevel("第四档");
        // DataEntity d15 = new DataEntity();
        // d15.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d15.setMonth(Month.JANUARY);
        // d15.setCompanyName("湖里城建");
        // d15.setParentProject("市政运营中心");
        // d15.setProject("空的");
        // d15.setScore("93");
        // d15.setLevel("第五档");

        // DataEntity d16 = new DataEntity();
        // d16.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d16.setMonth(Month.JANUARY);
        // d16.setCompanyName("建阳城建");
        // d16.setParentProject("园林服务中心");
        // d16.setProject("园林绿化");
        // d16.setScore("70");
        // d16.setLevel("第十档");
        // DataEntity d17 = new DataEntity();
        // d17.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d17.setMonth(Month.JANUARY);
        // d17.setCompanyName("建阳城建");
        // d17.setParentProject("区环卫服务中心");
        // d17.setProject("城区保洁");
        // d17.setScore("71");
        // d17.setLevel("第十一档");
        // DataEntity d18 = new DataEntity();
        // d18.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d18.setMonth(Month.JANUARY);
        // d18.setCompanyName("建阳城建");
        // d18.setParentProject("区环卫服务中心");
        // d18.setProject("赤岸巷道保洁");
        // d18.setScore("72");
        // d18.setLevel("第十二档");
        // DataEntity d19 = new DataEntity();
        // d19.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d19.setMonth(Month.JANUARY);
        // d19.setCompanyName("建阳城建");
        // d19.setParentProject("区环卫服务中心");
        // d19.setProject("转运站设施维护保养");
        // d19.setScore("73");
        // d19.setLevel("第十三档");
        // DataEntity d110 = new DataEntity();
        // d110.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d110.setMonth(Month.JANUARY);
        // d110.setCompanyName("建阳城建");
        // d110.setParentProject("区环卫服务中心");
        // d110.setProject("转运站设施维护保养");
        // d110.setScore("74");
        // d110.setLevel("第十四档");

        DataEntity d111 = new DataEntity();
        d111.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d111.setMonth(Month.JANUARY);
        d111.setCompanyName("步道公司");
        d111.setParentProject("业主考评");
        d111.setProject("仙岳公园");
        d111.setScore("80");
        d111.setLevel("第二十档");
        DataEntity d112 = new DataEntity();
        d112.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d112.setMonth(Month.JANUARY);
        d112.setCompanyName("步道公司");
        d112.setParentProject("业主考评");
        d112.setProject("狐尾山公园");
        d112.setScore("81");
        d112.setLevel("第二十一档");
        DataEntity d113 = new DataEntity();
        d113.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d113.setMonth(Month.JANUARY);
        d113.setCompanyName("步道公司");
        d113.setParentProject("业主考评");
        d113.setProject("健康步道及自行车");
        d113.setScore("82");
        d113.setLevel("第二十二档");
        // DataEntity d114 = new DataEntity();
        // d114.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d114.setMonth(Month.JANUARY);
        // d114.setCompanyName("步道公司");
        // d114.setParentProject("市级考评");
        // d114.setProject("仙岳公园");
        // d114.setScore("83");
        // d114.setLevel("第二十三档");
        // DataEntity d115 = new DataEntity();
        // d115.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d115.setMonth(Month.JANUARY);
        // d115.setCompanyName("步道公司");
        // d115.setParentProject("市级考评");
        // d115.setProject("狐尾山公园");
        // d115.setScore("84");
        // d115.setLevel("第二十四档");
        // DataEntity d116 = new DataEntity();
        // d116.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d116.setMonth(Month.JANUARY);
        // d116.setCompanyName("步道公司");
        // d116.setParentProject("市级考评");
        // d116.setProject("健康步道");
        // d116.setScore("85");
        // d116.setLevel("第二十五档");

        // dataListJanuary.add(d11);
        // dataListJanuary.add(d12);
        // dataListJanuary.add(d13);
        // dataListJanuary.add(d14);
        // dataListJanuary.add(d15);
        // dataListJanuary.add(d16);
        // dataListJanuary.add(d17);
        // dataListJanuary.add(d18);
        // dataListJanuary.add(d19);
        // dataListJanuary.add(d110);
        dataListJanuary.add(d111);
        dataListJanuary.add(d112);
        dataListJanuary.add(d113);
        // dataListJanuary.add(d114);
        // dataListJanuary.add(d115);
        // dataListJanuary.add(d116);


        // February
        List<DataEntity> dataListFebruary = new ArrayList<>();
        DataEntity d21 = new DataEntity();
        d21.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d21.setMonth(Month.FEBRUARY);
        d21.setCompanyName("湖里城建");
        d21.setParentProject("步道公司");
        d21.setProject("仙岳公园");
        d21.setScore("97");
        d21.setLevel("第一档");
        DataEntity d22 = new DataEntity();
        d22.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d22.setMonth(Month.FEBRUARY);
        d22.setCompanyName("湖里城建");
        d22.setParentProject("环卫运营中心");
        d22.setProject("公厕");
        d22.setScore("96");
        d22.setLevel("第二档");
        DataEntity d23 = new DataEntity();
        d23.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d23.setMonth(Month.FEBRUARY);
        d23.setCompanyName("湖里城建");
        d23.setParentProject("环卫运营中心");
        d23.setProject("道路保洁");
        d23.setScore("91");
        d23.setLevel("第三档");
        DataEntity d24 = new DataEntity();
        d24.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d24.setMonth(Month.FEBRUARY);
        d24.setCompanyName("湖里城建");
        d24.setParentProject("园林绿化运营中心");
        d24.setProject("公园中心");
        d24.setScore("90");
        d24.setLevel("第四档");
        // DataEntity d25 = new DataEntity();
        // d25.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d25.setMonth(Month.FEBRUARY);
        // d25.setCompanyName("湖里城建");
        // d25.setParentProject("市政运营中心");
        // d25.setProject("空的");
        // d25.setScore("93");
        // d25.setLevel("第五档");

        DataEntity d26 = new DataEntity();
        d26.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d26.setMonth(Month.FEBRUARY);
        d26.setCompanyName("建阳城建");
        d26.setParentProject("园林服务中心");
        d26.setProject("园林绿化");
        d26.setScore("70");
        d26.setLevel("第十档");
        DataEntity d27 = new DataEntity();
        d27.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d27.setMonth(Month.FEBRUARY);
        d27.setCompanyName("建阳城建");
        d27.setParentProject("区环卫服务中心");
        d27.setProject("城区保洁");
        d27.setScore("71");
        d27.setLevel("第十一档");
        DataEntity d28 = new DataEntity();
        d28.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d28.setMonth(Month.FEBRUARY);
        d28.setCompanyName("建阳城建");
        d28.setParentProject("区环卫服务中心");
        d28.setProject("赤岸巷道保洁");
        d28.setScore("72");
        d28.setLevel("第十二档");
        DataEntity d29 = new DataEntity();
        d29.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d29.setMonth(Month.FEBRUARY);
        d29.setCompanyName("建阳城建");
        d29.setParentProject("区环卫服务中心");
        d29.setProject("转运站设施维护保养");
        d29.setScore("73");
        d29.setLevel("第十三档");
        // DataEntity d210 = new DataEntity();
        // d210.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d210.setMonth(Month.FEBRUARY);
        // d210.setCompanyName("建阳城建");
        // d210.setParentProject("区环卫服务中心");
        // d210.setProject("转运站设施维护保养");
        // d210.setScore("74");
        // d210.setLevel("第十四档");

        DataEntity d211 = new DataEntity();
        d211.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d211.setMonth(Month.FEBRUARY);
        d211.setCompanyName("步道公司");
        d211.setParentProject("业主考评");
        d211.setProject("仙岳公园");
        d211.setScore("80");
        d211.setLevel("第二十档");
        DataEntity d212 = new DataEntity();
        d212.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d212.setMonth(Month.FEBRUARY);
        d212.setCompanyName("步道公司");
        d212.setParentProject("业主考评");
        d212.setProject("狐尾山公园");
        d212.setScore("81");
        d212.setLevel("第二十一档");
        // DataEntity d213 = new DataEntity();
        // d213.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d213.setMonth(Month.FEBRUARY);
        // d213.setCompanyName("步道公司");
        // d213.setParentProject("业主考评");
        // d213.setProject("健康步道及自行车");
        // d213.setScore("82");
        // d213.setLevel("第二十二档");
        DataEntity d214 = new DataEntity();
        d214.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        d214.setMonth(Month.FEBRUARY);
        d214.setCompanyName("步道公司");
        d214.setParentProject("市级考评");
        d214.setProject("仙岳公园");
        d214.setScore("83");
        d214.setLevel("第二十三档");
        // DataEntity d215 = new DataEntity();
        // d215.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d215.setMonth(Month.FEBRUARY);
        // d215.setCompanyName("步道公司");
        // d215.setParentProject("市级考评");
        // d215.setProject("狐尾山公园");
        // d215.setScore("84");
        // d215.setLevel("第二十四档");
        // DataEntity d216 = new DataEntity();
        // d216.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
        // d216.setMonth(Month.FEBRUARY);
        // d216.setCompanyName("步道公司");
        // d216.setParentProject("市级考评");
        // d216.setProject("健康步道");
        // d216.setScore("85");
        // d216.setLevel("第二十五档");

        dataListFebruary.add(d21);
        dataListFebruary.add(d22);
        dataListFebruary.add(d23);
        dataListFebruary.add(d24);
        // dataListFebruary.add(d25);
        dataListFebruary.add(d26);
        dataListFebruary.add(d27);
        dataListFebruary.add(d28);
        dataListFebruary.add(d29);
        // dataListFebruary.add(d210);
        dataListFebruary.add(d211);
        dataListFebruary.add(d212);
        // dataListFebruary.add(d213);
        dataListFebruary.add(d214);
        // dataListFebruary.add(d215);
        // dataListFebruary.add(d216);

        List<List<DataEntity>> dataCollection = new ArrayList<>();
        // 数据集合都加进来，测试数据两个月，就加了两行
        dataCollection.add(dataListJanuary);
        dataCollection.add(dataListFebruary);

        List<List<DataEntity>> realDataCollection = new ArrayList<>();
        Map<String, Map<String, Map<String, List<DataEntity>>>> companyMap = new HashMap<>();
        for(int i = 0; i < dataCollection.size(); i++) {
            List<DataEntity> entities = dataCollection.get(i);
            for (int j = 0; j < entities.size(); j++) {
                DataEntity data = entities.get(j);
                if (!companyMap.containsKey(data.getCompanyName())) {
                    Map<String, Map<String, List<DataEntity>>> parentProjects = new HashMap<>();
                    companyMap.put(data.getCompanyName(), parentProjects);
                }
                Map<String, Map<String, List<DataEntity>>> parentProjects = companyMap.get(data.getCompanyName());
                if (!parentProjects.containsKey(data.getParentProject())) {
                    Map<String, List<DataEntity>> projects = new HashMap<>();
                    parentProjects.put(data.getParentProject(), projects);
                }
                Map<String, List<DataEntity>> projects = parentProjects.get(data.getParentProject());
                if (!projects.containsKey(data.getProject())) {
                    List<DataEntity> datas = new ArrayList<>();
                    datas.add(data);
                    projects.put(data.getProject(), datas);
                } else {
                    projects.get(data.getProject()).add(data);
                }
            }
            realDataCollection.add(new ArrayList<>());
        }

        Set<String> projectSet = new HashSet<>();
        List<DataEntity> headers = new ArrayList<>();
        // 填充空白
        for(String compnayName: companyMap.keySet()) {
            Map<String, Map<String, List<DataEntity>>> parentProjects = companyMap.get(compnayName);
            for (String parentProjectKey: parentProjects.keySet()) {
                Map<String, List<DataEntity>> projects = parentProjects.get(parentProjectKey);
                for (String projectKey: projects.keySet()) {
                    projectSet.add(compnayName + parentProjectKey + projectKey);
                    DataEntity dataEntity = new DataEntity();
                    dataEntity.setTitle("2023年公共服务板块国企化管养成绩及档次汇总表");
                    dataEntity.setCompanyName(compnayName);
                    dataEntity.setParentProject(parentProjectKey);
                    dataEntity.setProject(projectKey);
                    headers.add(dataEntity);
                }
            }
        }

        for(int i = 0; i < realDataCollection.size(); i++) {
            for (int j = 0; j < projectSet.size(); j++) {
                realDataCollection.get(i).add(new DataEntity());
            }
        }

        // 填充data
        int projectIndex = 0;
        for(String compnayName: companyMap.keySet()) {
            Map<String, Map<String, List<DataEntity>>> parentProjects = companyMap.get(compnayName);
            for (String parentProjectKey: parentProjects.keySet()) {
                Map<String, List<DataEntity>> projects = parentProjects.get(parentProjectKey);
                for (String projectKey: projects.keySet()) {
                    // datas是当前项目下，多个月的数据集合，是列集合。需要判断所有的列是否都填充上了，没有数据的需要加入空对象。
                    List<DataEntity> datas = projects.get(projectKey);
                    // 取出已经有数据的，写入到realDataCollection里面
                    for (int j = 0; j < datas.size(); j++) {
                        DataEntity data = datas.get(j);
                        DataEntity realData = realDataCollection.get(data.getMonth().getValue() - 1).get(projectIndex);
                        realData.setCompanyName(data.getCompanyName());
                        realData.setParentProject(data.getParentProject());
                        realData.setProject(data.getProject());
                        realData.setMonth(data.getMonth());
                        realData.setScore(data.getScore());
                        realData.setLevel(data.getLevel());
                        realData.setTitle(data.getTitle());
                    }
                    projectIndex++;
                }
            }
        }

        for(int i = 0; i < realDataCollection.size(); i++) {
            // dataMap.put(Month.JANUARY.getNameLan(), dataListJanuary);
            // dataMap.put(Month.FEBRUARY.getNameLan(), dataListFebruary);
            dataMap.put(i + 1, realDataCollection.get(i));
        }
        
        scoreLevelExcelData.setDataMap(dataMap);
        scoreLevelExcelData.setHeads(headers);
    }

    @Test
    public void exportExcel() {
        List<List<String>> headers = header();
        for (List<String> hs: headers) {
            for (String h: hs) {
                System.out.println(h);
            }
        }

        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        headWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        WriteCellStyle headWriteCellStyleData = new WriteCellStyle();
        // 设置头部样式策略
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, headWriteCellStyleData);

        EasyExcel.write("output.xlsx")
            .registerWriteHandler(horizontalCellStyleStrategy)
            // 这里放入动态头
            .head(headers).sheet("模板")
            // 当然这里数据也可以用 List<List<String>> 去传入
            .doWrite(data());
    }

    public List<List<String>> header() {
        List<List<String>> headers = new ArrayList<>();
        headers.add(Arrays.asList("2023年公共服务板块国企化管养成绩及档次汇总表", "月份项目", "月份项目", "月份项目", "月份项目"));
        for (DataEntity dataEntity :scoreLevelExcelData.heads) {
            headers.add(Arrays.asList(dataEntity.getTitle(), dataEntity.getCompanyName(), dataEntity.getParentProject(), dataEntity.getProject(), "成绩"));
            headers.add(Arrays.asList(dataEntity.getTitle(), dataEntity.getCompanyName(), dataEntity.getParentProject(), dataEntity.getProject(), "档次"));
        }
        return headers;
    }
    // private List<List<String>> head() {
    //     List<List<String>> list = new ArrayList<List<String>>();
    //     List<String> head0 = new ArrayList<String>();
    //     head0.add("字符串" + System.currentTimeMillis());
    //     List<String> head1 = new ArrayList<String>();
    //     head1.add("数字" + System.currentTimeMillis());
    //     List<String> head2 = new ArrayList<String>();
    //     head2.add("日期" + System.currentTimeMillis());
    //     list.add(head0);
    //     list.add(head1);
    //     list.add(head2);
    //     return list;
    // }

    public List<List<String>> data() {
        List<List<String>> datas = new ArrayList<>();
        Month[] ms = Month.values();
        for (int i = 0; i < ms.length; i++) {
            Month m = ms[i];
            List<DataEntity> dataList = scoreLevelExcelData.dataMap.get(m.getValue());
            List<String> excelDataList = new ArrayList<>();
            excelDataList.add(m.getNameLan());
            if (dataList != null && dataList.size() > 0) {
                for (DataEntity excelData: dataList) {
                    excelDataList.add(excelData.getScore());
                    excelDataList.add(excelData.getLevel());
                }
            }
            datas.add(excelDataList);
        }
        return datas;
    }
    // private List<DemoData> data() {
    //     List<DemoData> list = ListUtils.newArrayList();
    //     for (int i = 0; i < 10; i++) {
    //         DemoData data = new DemoData();
    //         data.setString("字符串" + i);
    //         data.setDate(new Date());
    //         data.setDoubleData(0.56);
    //         list.add(data);
    //     }
    //     return list;
    // }
}

@Data
class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
