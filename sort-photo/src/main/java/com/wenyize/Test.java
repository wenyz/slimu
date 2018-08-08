package com.wenyize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public static void main(String[] args) {

        HashValueDataBase dataBase =HashValueDataBase.getInstance();

        dataBase.add("111111",new ImageFile("1111", "111111"));
        dataBase.add("222222",new ImageFile("2222", "222222"));

        System.out.println(dataBase.toString());
        System.out.println(dataBase.toJsonString());

        dataBase.write();
//
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(dataBase);
        System.out.println("sleep over");
        dataBase.load();
        System.out.println(dataBase);
        System.out.println(dataBase.toJsonString());



//        String js = dataBase.toJsonString();
//        try {
//            HashValueDataBase tt = JSON.parseObject(js, HashValueDataBase.class);
//
//            System.out.println(tt.toString());
//            System.out.println(tt.toJsonString());
//        } catch (JSONException e) {
//            System.out.println("文件字符错乱，不符合对象规则");
//        }


    }

//    public static void main(String[] args) {
//
//        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss X:00 yyyy", Locale.ENGLISH);
//        String aaa = "Fri Dec 11 13:15:44 +08:00 2015";
//        try {
//           // Date tt = dateFormat.parse(aaa);
//            //System.out.println(tt);
//
//            Date eee = Calendar.getInstance().getTime();
//
//            System.out.println(dateFormat.format(eee));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
