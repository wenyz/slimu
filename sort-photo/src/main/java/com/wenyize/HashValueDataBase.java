package com.wenyize;

import com.alibaba.fastjson.JSON;
import com.drew.lang.StringUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HashValueDataBase implements Serializable {

    private String basePath = "E://test//des";
    private String databaseFilePath = basePath + File.separator + ".hashdata";
    private String imgDataPath = basePath + File.separator + "photo";
    private String vDataPath = basePath + File.separator + "video";
    private static transient HashValueDataBase dataBase;
    private Map<String, HashFile> fileMap;

    private HashValueDataBase() {
        fileMap= new HashMap<>();
        System.out.println("1111111111");
    }

    public static synchronized HashValueDataBase getInstance() {
        if (dataBase == null) {
            dataBase = new HashValueDataBase();
        }
        return dataBase;

    }

    public void load() {

        File ff = new File(databaseFilePath);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(ff))) {

            Long aaa = ff.length();
            // TODO 当文件查过int大小时，需要寻找别的处理方法
            byte[] bytes = new byte[aaa.intValue()];
            bis.read(bytes);
            String ttt = StringUtil.fromStream(new ByteArrayInputStream(bytes));
            //System.out.println();
            //System.out.println("aaaaaa"+ttt );
            HashValueDataBase kkkk = JSON.parseObject(ttt, HashValueDataBase.class);
            //fileMap = kkkk.getFileMap();
            //dataBase = kkkk;
            //System.out.println("kkkkk" + kkkk.toJsonString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void write() {
        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(databaseFilePath))) {
            bos.write(toJsonString().getBytes());
            bos.flush();
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

    public static HashValueDataBase getDataBase() {
        return dataBase;
    }

    public Map<String, HashFile> getFileMap() {
        return fileMap;
    }
}
