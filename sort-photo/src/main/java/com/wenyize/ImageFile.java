package com.wenyize;

public class ImageFile extends AbstractHashFile {
    private String absolutePath;
    private String hashValue;

    public ImageFile(String hashValue, String absolutePath) {
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
        StringBuilder sb = new StringBuilder("");
        sb.append(hashValue);
        sb.append(FIELD_SEPERATOR);
        sb.append(absolutePath);
        sb.append(LINE_SEPERATOR);
        return sb.toString();
    }

    public static ImageFile fromLine(String line) {
        String[] tmp = line.split(FIELD_SEPERATOR);
        return new ImageFile(tmp[0], tmp[1]);
    }
}
