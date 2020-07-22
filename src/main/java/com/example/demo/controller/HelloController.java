package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class HelloController {
    @Autowired
    private KafkaSender kafkaSender;
    @Autowired
    private DroolsTestController droolsTestController;
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    @ResponseBody
    @RequestMapping("/hello")
    public  String hello(){
        return "Hello World!!!";
    }
    @RequestMapping("/upload")
    private String upload() {
        return "upload";
    }
    @RequestMapping("/success")
    private String success() {
        return "success";
    }
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "D:\\djl\\demo\\target\\classes\\tb\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            droolsTestController.drools();
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";
    }
//测试kafka方法
    @RequestMapping("/kafka")
    @ResponseBody
    public String testKafka() {
            kafkaSender.send();
            return "kafka success";
        }
}
