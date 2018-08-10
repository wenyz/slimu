package com.wenyize;

public abstract class AbstractHashFile implements HashFile{
    protected  static final String FIELD_SEPERATOR = ",";
    protected  static final String LINE_SEPERATOR = "\n";

    @Override
    public String toLine() {
        return null;
    }
}
