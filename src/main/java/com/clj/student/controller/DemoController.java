package com.clj.student.controller;

import com.clj.student.model.KafkaMessage;
import com.clj.student.model.po.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {
    Logger logger = LoggerFactory.getLogger(DemoController.class);
    @GetMapping("/hello/{id}")
    public Student getPerson(@PathVariable Long id) {
        logger.info("get person:" + id);
        return new Student();
    }

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    // 1.线程池
    // 2. 基本类型  atomic， collection concur
    // threadlocal

    @GetMapping("redis/get")
    public String get(String key) {
        // string
        // set test1 value1
        redisTemplate.opsForValue().set("test1", "value1");
        // get test1
        String test1 = redisTemplate.opsForValue().get("test1");
        logger.info("set and get:" + test1);
        // hash
        // hset uesr name clj location beijing
        Map<String, String> hash1 = new HashMap<>();
        hash1.put("name", "clj");
        hash1.put("location", "beijing");
        redisTemplate.opsForHash().putAll("user", hash1);
        // hget user name
        String name = redisTemplate.<String,String>opsForHash().get("user", "name");
        logger.info("hash name:" + name);
        redisTemplate.opsForHash().entries("key");
        String s = redisTemplate.opsForValue().get(key);
        return s;
    }

    @GetMapping("/redis/set")
    public String set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "success";
    }

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // kafka
    @GetMapping("/send_product")
    public String sendProduct() throws JsonProcessingException {
        KafkaMessage km = new KafkaMessage();
        km.setId(1L);
        km.setServiceId("productInfo");

        ObjectMapper mapper = new ObjectMapper();
        String kafkaJsonMessage = mapper.writeValueAsString(km);
        kafkaTemplate.send("eshop-cache", kafkaJsonMessage); // send message
        String res = "message is " + kafkaJsonMessage;
        logger.info(res);
        return res;
    }

    @GetMapping("/send_shop")
    public String sendshopmsg() throws JsonProcessingException {
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setId(1L);
        kafkaMessage.setServiceId("shopInfoService");

        ObjectMapper mapper = new ObjectMapper();
        String kafkaJsonMessage = mapper.writeValueAsString(kafkaMessage);
        kafkaTemplate.send("eshop-cache", kafkaJsonMessage); //使用kafka模板发送信息
        String res = "消息:【" + kafkaJsonMessage + "】发送成功 SUCCESS !";
        logger.info(res);
        return res;
    }

    @PostMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        String fileName = "中文模板.docx";
        //获得待下载文件的绝对路径
        String realPath = ResourceUtils.getURL("classpath:").getPath();
        //获取文件输入流
        FileInputStream fileInputStream = new FileInputStream(new File(realPath, fileName));
        String substring = fileName.substring(0, fileName.lastIndexOf("."));
        System.out.println(substring);
        //文件名包含中文时需要进行中文编码，否则会出现乱码问题
//        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        response.addHeader("Content-Disposition", "attachment;filename*=utf-8''" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
//        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        response.setContentType("application/octet-stream;utf-8");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        int len = 0;
        //设置一个缓冲区，大小取决于文件内容的大小
        byte[] buffer = new byte[1024];
        //每次读入缓冲区的数据，直到缓冲区无数据
        while ((len = fileInputStream.read(buffer)) != -1) {
            //输出缓冲区的数据
            servletOutputStream.write(buffer, 0, len);
        }
        servletOutputStream.close();
        fileInputStream.close();
    }

    @PostMapping("/download1")
    public ResponseEntity<InputStreamResource> download1(HttpServletResponse response) throws IOException {
        String fileName = "中文模板.docx";
        //获得待下载文件的绝对路径
        String realPath = ResourceUtils.getURL("classpath:").getPath();
        //获取文件输入流
        FileInputStream fileInputStream = new FileInputStream(new File(realPath, fileName));
        InputStreamResource isr = new InputStreamResource(fileInputStream);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment;filename*=utf-8''" + URLEncoder.encode("中文模板", StandardCharsets.UTF_8) + ".docx")
                .body(isr);
    }

}
