package com.wenyize;

public class VideoFile extends AbstractHashFile {
    private String absolutePath;
    private String hashValue;

    public VideoFile(String absolutePath, String hashValue) {
        this.absolutePath = absolutePath;
        this.hashValue = hashValue;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    @Override
    public String toLine() {
        return null;
    }
}
