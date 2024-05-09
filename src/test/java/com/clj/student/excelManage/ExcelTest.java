package com.clj.student.excelManage;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
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
    List<List<DataEntity>> dataCollection = new ArrayList<>();

    @BeforeEach
    public void init() {

        List<DataEntity> dataList1 = new ArrayList<>();
        // January
        DataEntity d1_1 = new DataEntity();
        d1_1.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_1.setCompanyName("湖里城建");
        d1_1.setParentProject("环卫运营中心");
        d1_1.setProject("道路保洁");
        d1_1.setMonth(Month.JANUARY);
        d1_1.setScore("100");
        d1_1.setAppropriation(1f);

        // FEBRUARY
        DataEntity d2_1 = new DataEntity();
        d2_1.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_1.setCompanyName("湖里城建");
        d2_1.setParentProject("环卫运营中心");
        d2_1.setProject("道路保洁");
        d2_1.setMonth(Month.FEBRUARY);
        d2_1.setScore("88");
        d2_1.setAppropriation(0.9f);

        // MARCH
        DataEntity d3_1 = new DataEntity();
        d3_1.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_1.setCompanyName("湖里城建");
        d3_1.setParentProject("环卫运营中心");
        d3_1.setProject("道路保洁");
        d3_1.setMonth(Month.MARCH);
        d3_1.setScore("100");
        d3_1.setAppropriation(1f);

        dataList1.add(d1_1);
        dataList1.add(d2_1);
        dataList1.add(d3_1);

        List<DataEntity> dataList2 = new ArrayList<>();
        // January
        DataEntity d1_2 = new DataEntity();
        d1_2.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_2.setCompanyName("湖里城建");
        d1_2.setParentProject("环卫运营中心");
        d1_2.setProject("公测");
        d1_2.setMonth(Month.JANUARY);
        d1_2.setScore("100");
        d1_2.setAppropriation(1f);

        // FEBRUARY
        DataEntity d2_2 = new DataEntity();
        d2_2.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_2.setCompanyName("湖里城建");
        d2_2.setParentProject("环卫运营中心");
        d2_2.setProject("公测");
        d2_2.setMonth(Month.FEBRUARY);
        d2_2.setScore("100");
        d2_2.setAppropriation(1f);

        // MARCH
        DataEntity d3_2 = new DataEntity();
        d3_2.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_2.setCompanyName("湖里城建");
        d3_2.setParentProject("环卫运营中心");
        d3_2.setProject("公测");
        d3_2.setMonth(Month.MARCH);
        d3_2.setScore("100");
        d3_2.setAppropriation(1f);

        dataList2.add(d1_2);
        dataList2.add(d2_2);
        dataList2.add(d3_2);

        List<DataEntity> dataList3 = new ArrayList<>();
        // January
        DataEntity d1_3 = new DataEntity();
        d1_3.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_3.setCompanyName("湖里城建");
        d1_3.setParentProject("市政运营中心");
        d1_3.setProject("市政");
        d1_3.setMonth(Month.JANUARY);
        d1_3.setScore("100");
        d1_3.setAppropriation(1f);

        // FEBRUARY
        DataEntity d2_3 = new DataEntity();
        d2_3.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_3.setCompanyName("湖里城建");
        d2_3.setParentProject("市政运营中心");
        d2_3.setProject("市政");
        d2_3.setMonth(Month.FEBRUARY);
        d2_3.setScore("100");
        d2_3.setAppropriation(1f);

        // MARCH
        DataEntity d3_3 = new DataEntity();
        d3_3.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_3.setCompanyName("湖里城建");
        d3_3.setParentProject("市政运营中心");
        d3_3.setProject("市政");
        d3_3.setMonth(Month.MARCH);
        d3_3.setScore("100");
        d3_3.setAppropriation(1f);

        dataList3.add(d1_3);
        dataList3.add(d2_3);
        dataList3.add(d3_3);

        List<DataEntity> dataList4 = new ArrayList<>();
        // January
        DataEntity d1_4 = new DataEntity();
        d1_4.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_4.setCompanyName("湖里城建");
        d1_4.setParentProject("园林绿化运营中心");
        d1_4.setProject("绿化");
        d1_4.setMonth(Month.JANUARY);
        d1_4.setScore("100");
        d1_4.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_4 = new DataEntity();
        d2_4.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_4.setCompanyName("湖里城建");
        d2_4.setParentProject("园林绿化运营中心");
        d2_4.setProject("绿化");
        d2_4.setMonth(Month.FEBRUARY);
        d2_4.setScore("100");
        d2_4.setAppropriation(1f);
        // MARCH
        DataEntity d3_4 = new DataEntity();
        d3_4.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_4.setCompanyName("湖里城建");
        d3_4.setParentProject("园林绿化运营中心");
        d3_4.setProject("绿化");
        d3_4.setMonth(Month.MARCH);
        d3_4.setScore("100");
        d3_4.setAppropriation(1f);

        dataList4.add(d1_4);
        dataList4.add(d2_4);
        dataList4.add(d3_4);

        List<DataEntity> dataList5 = new ArrayList<>();
        // January
        DataEntity d1_5 = new DataEntity();
        d1_5.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_5.setCompanyName("湖里城建");
        d1_5.setParentProject("园林绿化运营中心");
        d1_5.setProject("公园中心");
        d1_5.setMonth(Month.JANUARY);
        d1_5.setScore("100");
        d1_5.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_5 = new DataEntity();
        d2_5.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_5.setCompanyName("湖里城建");
        d2_5.setParentProject("园林绿化运营中心");
        d2_5.setProject("公园中心");
        d2_5.setMonth(Month.FEBRUARY);
        d2_5.setScore("100");
        d2_5.setAppropriation(1f);
        // MARCH
        DataEntity d3_5 = new DataEntity();
        d3_5.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_5.setCompanyName("湖里城建");
        d3_5.setParentProject("园林绿化运营中心");
        d3_5.setProject("公园中心");
        d3_5.setMonth(Month.MARCH);
        d3_5.setScore("100");
        d3_5.setAppropriation(1f);

        dataList5.add(d1_5);
        dataList5.add(d2_5);
        dataList5.add(d3_5);

        List<DataEntity> dataList6 = new ArrayList<>();
        // January
        DataEntity d1_6 = new DataEntity();
        d1_6.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_6.setCompanyName("建阳城建");
        d1_6.setParentProject("环卫运营部");
        d1_6.setProject("市政道路、公园清扫保洁(含垃圾清运等)运营服务项目");
        d1_6.setMonth(Month.JANUARY);
        d1_6.setScore("100");
        d1_6.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_6 = new DataEntity();
        d2_6.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_6.setCompanyName("建阳城建");
        d2_6.setParentProject("环卫运营部");
        d2_6.setProject("市政道路、公园清扫保洁(含垃圾清运等)运营服务项目");
        d2_6.setMonth(Month.FEBRUARY);
        d2_6.setScore("100");
        d2_6.setAppropriation(1f);
        // MARCH
        DataEntity d3_6 = new DataEntity();
        d3_6.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_6.setCompanyName("建阳城建");
        d3_6.setParentProject("环卫运营部");
        d3_6.setProject("市政道路、公园清扫保洁(含垃圾清运等)运营服务项目");
        d3_6.setMonth(Month.MARCH);
        d3_6.setScore("100");
        d3_6.setAppropriation(1f);

        dataList6.add(d1_6);
        dataList6.add(d2_6);
        dataList6.add(d3_6);


        List<DataEntity> dataList7 = new ArrayList<>();
        // January
        DataEntity d1_7 = new DataEntity();
        d1_7.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_7.setCompanyName("建阳城建");
        d1_7.setParentProject("环卫运营部");
        d1_7.setProject("转运站设施维护保养");
        d1_7.setMonth(Month.JANUARY);
        d1_7.setScore("100");
        d1_7.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_7 = new DataEntity();
        d2_7.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_7.setCompanyName("建阳城建");
        d2_7.setParentProject("环卫运营部");
        d2_7.setProject("转运站设施维护保养");
        d2_7.setMonth(Month.FEBRUARY);
        d2_7.setScore("100");
        d2_7.setAppropriation(1f);
        // MARCH
        DataEntity d3_7 = new DataEntity();
        d3_7.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_7.setCompanyName("建阳城建");
        d3_7.setParentProject("环卫运营部");
        d3_7.setProject("转运站设施维护保养");
        d3_7.setMonth(Month.MARCH);
        d3_7.setScore("100");
        d3_7.setAppropriation(1f);

        dataList7.add(d1_7);
        dataList7.add(d2_7);
        dataList7.add(d3_7);

        List<DataEntity> dataList8 = new ArrayList<>();
        // January
        DataEntity d1_8 = new DataEntity();
        d1_8.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_8.setCompanyName("建阳城建");
        d1_8.setParentProject("环卫运营部");
        d1_8.setProject("童游街道垃圾分类");
        d1_8.setMonth(Month.JANUARY);
        d1_8.setScore("100");
        d1_8.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_8 = new DataEntity();
        d2_8.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_8.setCompanyName("建阳城建");
        d2_8.setParentProject("环卫运营部");
        d2_8.setProject("童游街道垃圾分类");
        d2_8.setMonth(Month.FEBRUARY);
        d2_8.setScore("");
        d2_8.setAppropriation(1f);
        // MARCH
        DataEntity d3_8 = new DataEntity();
        d3_8.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_8.setCompanyName("建阳城建");
        d3_8.setParentProject("环卫运营部");
        d3_8.setProject("童游街道垃圾分类");
        d3_8.setMonth(Month.MARCH);
        d3_8.setScore("");
        d3_8.setAppropriation(1f);
        d3_8.setComment("合同至2024年1月31日期满");

        dataList8.add(d1_8);
        dataList8.add(d2_8);
        dataList8.add(d3_8);

        List<DataEntity> dataList9 = new ArrayList<>();
        // January
        DataEntity d1_9 = new DataEntity();
        d1_9.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_9.setCompanyName("建阳城建");
        d1_9.setParentProject("市政运营部");
        d1_9.setProject("路灯维护");
        d1_9.setMonth(Month.JANUARY);
        d1_9.setScore("100");
        d1_9.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_9 = new DataEntity();
        d2_9.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_9.setCompanyName("建阳城建");
        d2_9.setParentProject("市政运营部");
        d2_9.setProject("路灯维护");
        d2_9.setMonth(Month.FEBRUARY);
        d2_9.setScore("100");
        d2_9.setAppropriation(1f);
        // MARCH
        DataEntity d3_9 = new DataEntity();
        d3_9.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_9.setCompanyName("建阳城建");
        d3_9.setParentProject("市政运营部");
        d3_9.setProject("路灯维护");
        d3_9.setMonth(Month.MARCH);
        d3_9.setScore("100");
        d3_9.setAppropriation(1f);

        dataList9.add(d1_9);
        dataList9.add(d2_9);
        dataList9.add(d3_9);

        List<DataEntity> dataList10 = new ArrayList<>();
        // January
        DataEntity d1_10 = new DataEntity();
        d1_10.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_10.setCompanyName("建阳城建");
        d1_10.setParentProject("市政运营部");
        d1_10.setProject("夜景照明");
        d1_10.setMonth(Month.JANUARY);
        d1_10.setScore("100");
        d1_10.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_10 = new DataEntity();
        d2_10.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_10.setCompanyName("建阳城建");
        d2_10.setParentProject("市政运营部");
        d2_10.setProject("夜景照明");
        d2_10.setMonth(Month.FEBRUARY);
        d2_10.setScore("100");
        d2_10.setAppropriation(1f);
        // MARCH
        DataEntity d3_10 = new DataEntity();
        d3_10.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_10.setCompanyName("建阳城建");
        d3_10.setParentProject("市政运营部");
        d3_10.setProject("夜景照明");
        d3_10.setMonth(Month.MARCH);
        d3_10.setScore("100");
        d3_10.setAppropriation(1f);

        dataList10.add(d1_10);
        dataList10.add(d2_10);
        dataList10.add(d3_10);

        List<DataEntity> dataList11 = new ArrayList<>();
        // January
        DataEntity d1_11 = new DataEntity();
        d1_11.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_11.setCompanyName("建阳城建");
        d1_11.setParentProject("市政运营部");
        d1_11.setProject("排水设施");
        d1_11.setMonth(Month.JANUARY);
        d1_11.setScore("100");
        d1_11.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_11 = new DataEntity();
        d2_11.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_11.setCompanyName("建阳城建");
        d2_11.setParentProject("市政运营部");
        d2_11.setProject("排水设施");
        d2_11.setMonth(Month.FEBRUARY);
        d2_11.setScore("100");
        d2_11.setAppropriation(1f);
        // MARCH
        DataEntity d3_11 = new DataEntity();
        d3_11.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_11.setCompanyName("建阳城建");
        d3_11.setParentProject("市政运营部");
        d3_11.setProject("排水设施");
        d3_11.setMonth(Month.MARCH);
        d3_11.setScore("100");
        d3_11.setAppropriation(1f);

        dataList11.add(d1_11);
        dataList11.add(d2_11);
        dataList11.add(d3_11);

        List<DataEntity> dataList12 = new ArrayList<>();
        // January
        DataEntity d1_12 = new DataEntity();
        d1_12.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_12.setCompanyName("建阳城建");
        d1_12.setParentProject("绿化运营部");
        d1_12.setProject("园林绿化");
        d1_12.setMonth(Month.JANUARY);
        d1_12.setScore("100");
        d1_12.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_12 = new DataEntity();
        d2_12.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_12.setCompanyName("建阳城建");
        d2_12.setParentProject("绿化运营部");
        d2_12.setProject("园林绿化");
        d2_12.setMonth(Month.FEBRUARY);
        d2_12.setScore("100");
        d2_12.setAppropriation(1f);
        // MARCH
        DataEntity d3_12 = new DataEntity();
        d3_12.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_12.setCompanyName("建阳城建");
        d3_12.setParentProject("绿化运营部");
        d3_12.setProject("园林绿化");
        d3_12.setMonth(Month.MARCH);
        d3_12.setScore("100");
        d3_12.setAppropriation(1f);

        dataList12.add(d1_12);
        dataList12.add(d2_12);
        dataList12.add(d3_12);

        List<DataEntity> dataList13 = new ArrayList<>();
        // January
        DataEntity d1_13 = new DataEntity();
        d1_13.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_13.setCompanyName("步道公司");
        d1_13.setParentProject("仙岳公园");
        d1_13.setProject("林地专项考评");
        d1_13.setMonth(Month.JANUARY);
        d1_13.setScore("100");
        d1_13.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_13 = new DataEntity();
        d2_13.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_13.setCompanyName("步道公司");
        d2_13.setParentProject("仙岳公园");
        d2_13.setProject("林地专项考评");
        d2_13.setMonth(Month.FEBRUARY);
        d2_13.setScore("100");
        d2_13.setAppropriation(1f);
        // MARCH
        DataEntity d3_13 = new DataEntity();
        d3_13.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_13.setCompanyName("步道公司");
        d3_13.setParentProject("仙岳公园");
        d3_13.setProject("林地专项考评");
        d3_13.setMonth(Month.MARCH);
        d3_13.setScore("100");
        d3_13.setAppropriation(1f);

        dataList13.add(d1_13);
        dataList13.add(d2_13);
        dataList13.add(d3_13);

        List<DataEntity> dataList14 = new ArrayList<>();
        // January
        DataEntity d1_14 = new DataEntity();
        d1_14.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_14.setCompanyName("步道公司");
        d1_14.setParentProject("仙岳公园");
        d1_14.setProject("园容考评综合成绩");
        d1_14.setMonth(Month.JANUARY);
        d1_14.setScore("100");
        d1_14.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_14 = new DataEntity();
        d2_14.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_14.setCompanyName("步道公司");
        d2_14.setParentProject("仙岳公园");
        d2_14.setProject("园容考评综合成绩");
        d2_14.setMonth(Month.FEBRUARY);
        d2_14.setScore("100");
        d2_14.setAppropriation(1f);
        // MARCH
        DataEntity d3_14 = new DataEntity();
        d3_14.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_14.setCompanyName("步道公司");
        d3_14.setParentProject("仙岳公园");
        d3_14.setProject("园容考评综合成绩");
        d3_14.setMonth(Month.MARCH);
        d3_14.setScore("100");
        d3_14.setAppropriation(1f);

        dataList14.add(d1_14);
        dataList14.add(d2_14);
        dataList14.add(d3_14);

        List<DataEntity> dataList15 = new ArrayList<>();
        // January
        DataEntity d1_15 = new DataEntity();
        d1_15.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_15.setCompanyName("步道公司");
        d1_15.setParentProject("狐尾山公园");
        d1_15.setProject("园林绿化管养考核");
        d1_15.setMonth(Month.JANUARY);
        d1_15.setScore("100");
        d1_15.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_15 = new DataEntity();
        d2_15.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_15.setCompanyName("步道公司");
        d2_15.setParentProject("狐尾山公园");
        d2_15.setProject("园林绿化管养考核");
        d2_15.setMonth(Month.FEBRUARY);
        d2_15.setScore("100");
        d2_15.setAppropriation(1f);
        // MARCH
        DataEntity d3_15 = new DataEntity();
        d3_15.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_15.setCompanyName("步道公司");
        d3_15.setParentProject("狐尾山公园");
        d3_15.setProject("园林绿化管养考核");
        d3_15.setMonth(Month.MARCH);
        d3_15.setScore("100");
        d3_15.setAppropriation(1f);

        dataList15.add(d1_15);
        dataList15.add(d2_15);
        dataList15.add(d3_15);

        List<DataEntity> dataList16 = new ArrayList<>();
        // January
        DataEntity d1_16 = new DataEntity();
        d1_16.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_16.setCompanyName("步道公司");
        d1_16.setParentProject("狐尾山公园");
        d1_16.setProject("公厕专项考评");
        d1_16.setMonth(Month.JANUARY);
        d1_16.setScore("100");
        d1_16.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_16 = new DataEntity();
        d2_16.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_16.setCompanyName("步道公司");
        d2_16.setParentProject("狐尾山公园");
        d2_16.setProject("公厕专项考评");
        d2_16.setMonth(Month.FEBRUARY);
        d2_16.setScore("100");
        d2_16.setAppropriation(1f);
        // MARCH
        DataEntity d3_16 = new DataEntity();
        d3_16.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_16.setCompanyName("步道公司");
        d3_16.setParentProject("狐尾山公园");
        d3_16.setProject("公厕专项考评");
        d3_16.setMonth(Month.MARCH);
        d3_16.setScore("100");
        d3_16.setAppropriation(1f);

        dataList16.add(d1_16);
        dataList16.add(d2_16);
        dataList16.add(d3_16);

        List<DataEntity> dataList17 = new ArrayList<>();
        // January
        DataEntity d1_17 = new DataEntity();
        d1_17.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_17.setCompanyName("步道公司");
        d1_17.setParentProject("健康步道(含自行车道)");
        d1_17.setProject("运营管理");
        d1_17.setMonth(Month.JANUARY);
        d1_17.setScore("98");
        d1_17.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_17 = new DataEntity();
        d2_17.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_17.setCompanyName("步道公司");
        d2_17.setParentProject("健康步道(含自行车道)");
        d2_17.setProject("运营管理");
        d2_17.setMonth(Month.FEBRUARY);
        d2_17.setScore("45");
        d2_17.setAppropriation(1f);
        // MARCH
        DataEntity d3_17 = new DataEntity();
        d3_17.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_17.setCompanyName("步道公司");
        d3_17.setParentProject("健康步道(含自行车道)");
        d3_17.setProject("运营管理");
        d3_17.setMonth(Month.MARCH);
        d3_17.setScore("991");
        d3_17.setAppropriation(1f);
        // APRIL
        DataEntity d4_17 = new DataEntity();
        d4_17.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d4_17.setCompanyName("步道公司");
        d4_17.setParentProject("健康步道(含自行车道)");
        d4_17.setProject("运营管理");
        d4_17.setMonth(Month.APRIL);
        d4_17.setScore("33");
        d4_17.setAppropriation(1f);
        // MAY
        DataEntity d5_17 = new DataEntity();
        d5_17.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d5_17.setCompanyName("步道公司");
        d5_17.setParentProject("健康步道(含自行车道)");
        d5_17.setProject("运营管理");
        d5_17.setMonth(Month.MAY);
        d5_17.setScore("999");
        d5_17.setAppropriation(1f);
        // JUNE
        DataEntity d6_17 = new DataEntity();
        d6_17.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d6_17.setCompanyName("步道公司");
        d6_17.setParentProject("健康步道(含自行车道)");
        d6_17.setProject("运营管理");
        d6_17.setMonth(Month.JUNE);
        d6_17.setScore("66");
        d6_17.setAppropriation(1f);

        dataList17.add(d4_17);
        dataList17.add(d2_17);
        // dataList17.add(d1_17);
        dataList17.add(d3_17);
        // dataList17.add(d6_17);
        dataList17.add(d5_17);

        List<DataEntity> dataList18 = new ArrayList<>();
        // January
        DataEntity d1_18 = new DataEntity();
        d1_18.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d1_18.setCompanyName("步道公司");
        d1_18.setParentProject("健康步道");
        d1_18.setProject("绿地养护考核");
        d1_18.setMonth(Month.JANUARY);
        d1_18.setScore("100");
        d1_18.setAppropriation(1f);
        // FEBRUARY
        DataEntity d2_18 = new DataEntity();
        d2_18.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d2_18.setCompanyName("步道公司");
        d2_18.setParentProject("健康步道");
        d2_18.setProject("绿地养护考核");
        d2_18.setMonth(Month.FEBRUARY);
        d2_18.setScore("100");
        d2_18.setAppropriation(1f);
        // MARCH
        DataEntity d3_18 = new DataEntity();
        d3_18.setTitle("2024年公告服务板块国企化管养成绩汇总表");
        d3_18.setCompanyName("步道公司");
        d3_18.setParentProject("健康步道");
        d3_18.setProject("绿地养护考核");
        d3_18.setMonth(Month.MARCH);
        d3_18.setScore("100");
        d3_18.setAppropriation(1f);
        d3_18.setComment("测试用");

        dataList18.add(d1_18);
        dataList18.add(d2_18);
        dataList18.add(d3_18);

        // 数据集合都加进来

        dataCollection.add(dataList1);
        dataCollection.add(dataList2);
        dataCollection.add(dataList3);
        dataCollection.add(dataList4);
        dataCollection.add(dataList5);
        dataCollection.add(dataList6);
        dataCollection.add(dataList7);
        dataCollection.add(dataList8);
        dataCollection.add(dataList9);
        dataCollection.add(dataList10);
        dataCollection.add(dataList11);
        dataCollection.add(dataList12);
        dataCollection.add(dataList13);
        dataCollection.add(dataList14);
        dataCollection.add(dataList15);
        dataCollection.add(dataList16);
        dataCollection.add(dataList17);
        dataCollection.add(dataList18);
    }

    @Test
    public void exportExcel() {
        List<List<String>> headers = header();
        for (List<String> hs: headers) {
            for (String h: hs) {
                System.out.println(h);
            }
        }

        // WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        // headWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        // WriteCellStyle headWriteCellStyleData = new WriteCellStyle();
        // 设置头部样式策略
        // HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, headWriteCellStyleData);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(StyleUtils.getHeadStyle(), StyleUtils.getContentStyle());

        int quarterIndex = -1;
        for (int i = 0; i < dataCollection.size(); i++) {
            List<DataEntity> dataList = dataCollection.get(i);
            if (dataList.size() > 0) {
                DataEntity data = dataList.get(0);
                if (data.getParentProject().equals("健康步道(含自行车道)")) {
                    Collections.sort(dataList, new Comparator<DataEntity>() {
                        @Override
                        public int compare(DataEntity o1, DataEntity o2) {
                            Month o1Month = Month.valueOf(o1.getMonth().getName().toUpperCase());
                            Month o2Month = Month.valueOf(o2.getMonth().getName().toUpperCase());
                            return o1Month.compareTo(o2Month);
                        }
                    });
                } else {
                    continue;
                }
            }
            for (int j = 0; j < dataList.size(); j++) {
                DataEntity data = dataList.get(j);
                if (data.getParentProject().equals("健康步道(含自行车道)")) {
                    quarterIndex = i;
                    break;
                }
            }
        }

        MergeStrategy mergeStrategy = new MergeStrategy(dataCollection.size() + 3, quarterIndex + 4);
        CellStyleWriter cellStyleWriter = new CellStyleWriter(StyleUtils.getHeadStyle(), StyleUtils.getContentStyle());
        EasyExcel.write("output1.xlsx", ExcelData.class)
            .registerWriteHandler(horizontalCellStyleStrategy)
            .registerWriteHandler(cellStyleWriter)
            .registerWriteHandler(mergeStrategy)
            // 这里放入动态头
            .head(headers).sheet("模板")
            // 当然这里数据也可以用 List<List<String>> 去传入
            .doWrite(data());
    }

    public List<List<String>> header() {
        List<List<String>> headers = new ArrayList<>();
        List<String> headerTitle1 = new ArrayList<>();
        headerTitle1.add("2024年公告服务板块国企化管养成绩汇总表");
        headerTitle1.add("单位");
        headerTitle1.add("单位2");
        headerTitle1.add("单位3");
        List<String> headerTitle1b = new ArrayList<>();
        headerTitle1b.add("2024年公告服务板块国企化管养成绩汇总表");
        headerTitle1b.add("单位merged");
        headerTitle1b.add("单位2merged");
        headerTitle1b.add("单位3merged");
        List<String> headerTitle2 = new ArrayList<>();
        headerTitle2.add("2024年公告服务板块国企化管养成绩汇总表");
        headerTitle2.add("考评项目");
        headerTitle2.add("考评项目");
        headerTitle2.add("考评项目");
        // headers.add(Arrays.asList("2024年公告服务板块国企化管养成绩汇总表", "单位", "单位", "单位", "考评项目", "考评项目", "考评项目", "综合成绩", "(注:黑色字体默认为经费全额拨付，红色字体表示该成绩触及经费扣罚标准)"));
        headers.add(headerTitle1);
        headers.add(headerTitle1b);
        headers.add(headerTitle2);
        // headers.add(headerTitle3);
        for (int i = 0; i < Month.values().length + 1; i++) {
            List<String> headerSubTitle = new ArrayList<>();
            headerSubTitle.add("2024年公告服务板块国企化管养成绩汇总表");
            headerSubTitle.add("综合成绩");
            headerSubTitle.add("(注:黑色字体默认为经费全额拨付，红色字体表示该成绩触及经费扣罚标准)");
            if (i == Month.values().length) {
                headerSubTitle.add("备注");
            } else {
                Month m = Month.values()[i];
                headerSubTitle.add(m.getValue() + "月");
            }
            headers.add(headerSubTitle);
        }
        return headers;
    }

    public List<ExcelData> data() {
        List<ExcelData> datas = new ArrayList<>();
        
        for(List<DataEntity> dataList: dataCollection) {
            ExcelData excelData = new ExcelData();
            for (DataEntity data: dataList) {
                excelData.setCompanyName(data.getCompanyName());
                excelData.setParentProject(data.getParentProject());
                excelData.setProject(data.getProject());
                String score = data.getScore();
                if (score == null || score.isEmpty()) {
                    score = "/";
                } else if (data.getAppropriation() < 1) {
                    NumberFormat fmt = NumberFormat.getPercentInstance();
                    // fmt.setMaximumIntegerDigits(0);
                    score = data.getScore() + "\n" + "经费" + fmt.format(data.getAppropriation()) + "拨款";
                }

                if (data.getParentProject().equals("健康步道(含自行车道)")) {
                    if (data.getMonth().getName().equals("January") || data.getMonth().getName().equals("February") || data.getMonth().getName().equals("March")) {
                        excelData.setJanuary(score);
                        excelData.setFebruary(score);
                        excelData.setMarch(score);
                    } else if (data.getMonth().getName().equals("April") || data.getMonth().getName().equals("May") || data.getMonth().getName().equals("June")) {
                        excelData.setApril(score);
                        excelData.setMay(score);
                        excelData.setJune(score);
                    } else if (data.getMonth().getName().equals("July") || data.getMonth().getName().equals("August") || data.getMonth().getName().equals("September")) {
                        excelData.setJuly(score);
                        excelData.setAugust(score);
                        excelData.setSeptember(score);
                    } else if (data.getMonth().getName().equals("October") || data.getMonth().getName().equals("November") || data.getMonth().getName().equals("December")) {
                        excelData.setOctober(score);
                        excelData.setNovember(score);
                        excelData.setDecember(score);
                    }
                } else {
                    if (data.getMonth().getName().equals("January")) {
                        excelData.setJanuary(score);
                    } else if (data.getMonth().getName().equals("February")) {
                        excelData.setFebruary(score);
                    } else if (data.getMonth().getName().equals("March")) {
                        excelData.setMarch(score);
                    } else if (data.getMonth().getName().equals("April")) {
                        excelData.setApril(score);
                    } else if (data.getMonth().getName().equals("May")) {
                        excelData.setMay(score);
                    } else if (data.getMonth().getName().equals("June")) {
                        excelData.setJune(score);
                    } else if (data.getMonth().getName().equals("July")) {
                        excelData.setJuly(score);
                    } else if (data.getMonth().getName().equals("August")) {
                        excelData.setAugust(score);
                    } else if (data.getMonth().getName().equals("September")) {
                        excelData.setSeptember(score);
                    } else if (data.getMonth().getName().equals("October")) {
                        excelData.setOctober(score);
                    } else if (data.getMonth().getName().equals("November")) {
                        excelData.setNovember(score);
                    } else if (data.getMonth().getName().equals("December")) {
                        excelData.setDecember(score);
                    }
                }
                excelData.setComment(data.getComment());
            }
            datas.add(excelData);
        }

        return datas;
    }
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
