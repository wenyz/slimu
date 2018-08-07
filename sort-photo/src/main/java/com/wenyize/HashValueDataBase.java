package com.wenyize;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class HashValueDataBase {


    public static final String BASE_PATH = "E://test//des";
    public static final String DATABASE_FILE_PATH = BASE_PATH + File.separator + ".hashdata";
    public static final String IMG_DATA_PATH = BASE_PATH + File.separator + "photo";
    public static final String V_DATA_PATH = BASE_PATH + File.separator + "video";
    private static HashValueDataBase dataBase;
    private Map<String, HashFile> fileMap = new HashMap<>();

    private HashValueDataBase() {
    }

    public static synchronized HashValueDataBase getInstance() {
        if (dataBase == null) {
            dataBase = new HashValueDataBase();
        }
        return dataBase;

    }

    public void load() {

    }

    public void write() {

    }

    public void refresh() {

    }

    public void search() {

    }

    public void add(String md5s, HashFile hashFile) {
        fileMap.put(md5s, hashFile);
    }

    public void update() {

    }

    public void updateAll() {

    }

    public void delete() {

    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    public void remove(String md5s) {
        fileMap.remove(md5s);
    }
}
