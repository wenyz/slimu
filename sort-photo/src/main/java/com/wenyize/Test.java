package com.wenyize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

public class Test {

    public static void main(String[] args) {

        HashValueDataBase dataBase = new HashValueDataBase();

        dataBase.getImageFileList().add(new ImageFile("1111","111111"));
        dataBase.getImageFileList().add(new ImageFile("2222","222222"));

        System.out.println(dataBase.toString());
        System.out.println(dataBase.toJsonString());

        //String js = dataBase.toJsonString();
        String js = "123wr2fsf";
        try {
            HashValueDataBase  tt = JSON.parseObject(js,HashValueDataBase.class);

            System.out.println(tt.toString());
            System.out.println(tt.toJsonString());
        }catch (JSONException e){
            System.out.println("文件字符错乱，不符合对象规则");
        }



    }
}
