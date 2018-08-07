package com.wenyize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Test {

//    public static void main(String[] args) {
//
//        HashValueDataBase dataBase = new HashValueDataBase();
//
//        dataBase.getImageFileList().add(new ImageFile("1111", "111111"));
//        dataBase.getImageFileList().add(new ImageFile("2222", "222222"));
//
//        System.out.println(dataBase.toString());
//        System.out.println(dataBase.toJsonString());
//
//        //String js = dataBase.toJsonString();
//        String js = "123wr2fsf";
//        try {
//            HashValueDataBase tt = JSON.parseObject(js, HashValueDataBase.class);
//
//            System.out.println(tt.toString());
//            System.out.println(tt.toJsonString());
//        } catch (JSONException e) {
//            System.out.println("文件字符错乱，不符合对象规则");
//        }
//
//
//    }

    public static void main(String[] args) {

        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss X:00 yyyy", Locale.ENGLISH);
        String aaa = "Fri Dec 11 13:15:44 +08:00 2015";
        try {
           // Date tt = dateFormat.parse(aaa);
            //System.out.println(tt);

            Date eee = Calendar.getInstance().getTime();

            System.out.println(dateFormat.format(eee));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
