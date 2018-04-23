package com.qcloud.controller;

import com.qcloud.image.demo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author wenyz
 * <p>
 * @since 1.0
 */
@Controller
@EnableAutoConfiguration
public class SampleController {

    @Autowired
    Demo demo;

    @RequestMapping("/")
//    @ResponseBody
    String home(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getParameter("path");
        String fileName = request.getParameter("fileName");
        String keyword = request.getParameter("keyword");
//        demo.getResult();
        //响应头设置
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html;charset=UTF-8");
        return "index";
    }

    @RequestMapping("/all")
//    @ResponseBody
    void all(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getParameter("path");
        String fileName = request.getParameter("fileName");
        String keyWord = request.getParameter("keyword");
        String[] keyWords = keyWord.split(",");

        ArrayList<String > keyWordList = new ArrayList<>(Arrays.asList(keyWords));
        //响应头设置
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html;charset=UTF-8");
        demo.getResult(keyWordList,path);


    }

    @RequestMapping("/one")
//    @ResponseBody
    void one(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getParameter("path");
        String fileName = request.getParameter("fileName");
        String keyWord = request.getParameter("keyword");
        //响应头设置
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html;charset=UTF-8");
        demo.getOneResult(path,fileName,keyWord);
    }

//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SampleController.class, args);
//    }

}
