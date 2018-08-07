package com.wenyize;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

/**
 * 1.整理图片文件
 * 2.清除重复文件
 * 重复文件采用hash算法做比较
 * 为了减少后续的操作，采用一种追加的操作
 * 即建立一个基本集，该基本集以一个文件索引为基准
 * （这个文件索引应该具有校验功能）
 * （可以建立新规则，把图片重新排序组合）
 * 后面新来的图片文件追加到前面的基准集中
 * 3.做成一个命令行工具
 * <p>
 * //https://blog.csdn.net/u013752202/article/details/78568995
 */
public class Main {

    static final Properties properties = new Properties();

    static {
        String projectPath = System.getProperty("user.dir");
        try (FileInputStream fi = new FileInputStream(projectPath + "/src/main/resources/user.properties")) {
            properties.load(new InputStreamReader(fi, "utf-8"));
        } catch (Throwable t) {
        }
    }

//    static String[] IN_FOLUDAR = properties.getProperty("input").split(";");
//
//    static String OUT_FOluDAR =  HashValueDataBase.dataBaseFilePath;

    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);
        MainProcess process = new MainProcess(HashValueDataBase.getInstance());
        process.process("E://test//original");
        System.out.println(HashValueDataBase.getInstance().toJsonString());
    }


}
