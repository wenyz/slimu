package com.wenyize;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashValueDataBase {

    private static final String dataBaseFilePath="";
    private static HashValueDataBase dataBase;
    private Map<String,HashFile> fileMap = new HashMap<>();
    private HashValueDataBase(){}

    public static synchronized HashValueDataBase getInstance(){
        if (dataBase == null){
            dataBase = new HashValueDataBase();
        }
        return dataBase;

    }

    public void load(){

    }
    public void write(){

    }
    public void refresh(){

    }

    public void search(){

    }
    public void add(){

    }
    public void update(){

    }
    public void updateAll(){

    }
    public void delete(){

    }

    public  String toJsonString(){
       return JSON.toJSONString(this);
    }

}
