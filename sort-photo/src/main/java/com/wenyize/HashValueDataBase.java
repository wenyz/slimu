package com.wenyize;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// TODO 设计不当 有内存泄漏风险
public class HashValueDataBase implements Serializable {

    private static final String basePath = "E://test//des";
    private static final String databaseFilePath = basePath + File.separator + ".hashdata";
    private String imgDataPath = basePath + File.separator + "photo";
    private static transient HashValueDataBase dataBase;
    private String vDataPath = basePath + File.separator + "video";
    private String repeatPath = basePath + File.separator + "chongfu";
    private String logPath = basePath + File.separator + ".log";
    private static final Map<String, HashFile> fileMap = new HashMap<>();

    private FileWriter logWriter;

    private HashValueDataBase() {

    }

    public static synchronized HashValueDataBase getInstance() {
        if (dataBase == null) {
            dataBase = new HashValueDataBase();
        }
        return dataBase;
    }


    public void load() {
        Map<String, HashFile> tempMap = new HashMap<>();
        try (RandomAccessFile reader = new RandomAccessFile(databaseFilePath, "r")) {
            String line;
            while ((line = reader.readLine()) != null) {
                ImageFile file = ImageFile.fromLine(line);
                tempMap.put(file.getHashValue(), file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileMap.clear();
        fileMap.putAll(tempMap);
    }

    // TODO 一次性读，一次性写，太SB了，想想换个别的方法
    public void write() {
        try (RandomAccessFile writer = new RandomAccessFile(databaseFilePath, "rw")) {
            for (HashFile file : fileMap.values()) {
                writer.write(file.toLine().getBytes());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    public void remove(String md5s) {
        fileMap.remove(md5s);
    }

    public String toJsonString() {
        return JSON.toJSONString(HashValueDataBase.getInstance());
    }


    public String getBasePath() {
        return basePath;
    }

    public String getDatabaseFilePath() {
        return databaseFilePath;
    }

    public String getImgDataPath() {
        return imgDataPath;
    }

    public String getvDataPath() {
        return vDataPath;
    }

    public String getRepeatPath() {
        return repeatPath;
    }

    public Map<String, HashFile> getFileMap() {
        return fileMap;
    }

    public boolean isExists(String key) {
        return fileMap.get(key) == null ? false : true;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogWriter(FileWriter logWriter) {
        this.logWriter = logWriter;
    }
    public void closeLogWriter(){
        try {
            this.logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String msg){
        System.out.println(msg);
        if(logWriter != null){
            try {
                logWriter.write(msg);
                logWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
