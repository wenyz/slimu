package com.wenyize;

public class ImageFile implements HashFile {
    private String absolutePath;
    private String hashValue;

    public ImageFile(String absolutePath, String hashValue) {
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
}
