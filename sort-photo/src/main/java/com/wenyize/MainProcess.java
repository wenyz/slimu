package com.wenyize;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.png.PngMetadataReader;
import com.drew.imaging.png.PngProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainProcess {
    private HashValueDataBase dataBase;

    public MainProcess(HashValueDataBase dataBase) {
        this.dataBase = dataBase;
    }


    public void process(String inputPath) {

        File f = new File(dataBase.getDatabaseFilePath());
        if(f.exists()){
            dataBase.load();
        }

        Stack<String> stack = new Stack<>();
        stack.push(inputPath);

        while (!stack.isEmpty()) {

            String ele = stack.pop();

            File f1 = new File(ele);
            if (f1.isDirectory()) {

                File[] files = f1.listFiles();
                for (File file : files) {
                    if (file.isDirectory()) {
                        stack.push(file.getPath());
                    } else {
                        dealFile(file);
                    }
                }
            } else {
                dealFile(f1);
            }
        }

        // 写入文件
        dataBase.write();


//        for (String tempF : inputPaths) {
//            File f1 = new File(tempF);
//            File[] f2s = f1.listFiles();
//
//            for (File f2 : f2s) {
//
//                if (f2.isFile()) {
//
//                }
//            }
//        }
    }


    private String combineOutPath(Date date) {
        StringBuilder sb = new StringBuilder();
        sb.append(dataBase.getImgDataPath());

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        sb.append(ca.get(Calendar.YEAR) + File.separator);
        sb.append((ca.get(Calendar.MONTH) + 1) + File.separator);

        int max = getMaxNum(ca);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        sb.append(df.format(date));
        sb.append("-");
        sb.append(String.format("%04d", max + 1));
        //sb.append(".JPG");
        return sb.toString();
    }

    private int getMaxNum(Calendar date) {
        int max = 0;
        File fff = new File(dataBase.getImgDataPath()
                + date.get(Calendar.YEAR) + File.separator
                + (date.get(Calendar.MONTH) + 1));
        if (fff.exists()) {
            File[] ffs = fff.listFiles();
            for (File temp : ffs) {
                int tk = Integer.valueOf(temp.getName().substring(temp.getName().lastIndexOf("-") + 1
                        , temp.getName().lastIndexOf(".")));
                if (tk > max) {
                    max = tk;
                }
            }
        }
        return max;
    }

    /**
     * 处理 单张 图片
     *
     * @return void
     * @date 2015-7-25 下午7:30:47
     * [Exif IFD0] Date/Time - 2017:04:14 21:59:09
     */
    private Date readPicDate(File jpegFile, String extension) {
        //= new File("G:\\photo\\新建文件夹 (2)\\170414.35.JPG");
        Metadata metadata = null;
        Date result = null;
        boolean timeExistFlg = false;
        try {
            if (type.jpg.name().equals(extension)) {
                metadata = JpegMetadataReader.readMetadata(jpegFile);
            } else if (type.png.name().equals(extension)) {
                try {
                    metadata = PngMetadataReader.readMetadata(jpegFile);
                } catch (PngProcessingException e) {
                    e.printStackTrace();
                }
            }

            Iterator<Directory> it = metadata.getDirectories().iterator();
            while (it.hasNext()) {
                Directory exif = it.next();
                Iterator<Tag> tags = exif.getTags().iterator();
                while (tags.hasNext()) {
                    Tag tag = (Tag) tags.next();
                    // TODO 该TAG并不是所有图片都有
                    //if (tag.toString().contains("Date/Time")) {
                    if (tag.toString().contains("File Modified Date")) {

                        String[] temp = tag.toString().split("-");
                        //DateFormat dateFormat = new SimpleDateFormat(" yyyy:MM:dd HH:mm:ss");
                        DateFormat dateFormat = new SimpleDateFormat(" EEE MMM dd HH:mm:ss X:00 yyyy", Locale.ENGLISH);
                        try {
                            result = dateFormat.parse(temp[1]);
                            timeExistFlg = true;
                        } catch (ParseException e) {
                            dataBase.log(jpegFile.getName() + ": 时间转换错误");
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (!timeExistFlg) {
                dataBase.log(jpegFile.getName() + ": 该图片没有拍摄时间");
            }
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    private void copyFile(String oldPath, String newPath) throws Exception {
        int bytesum = 0;
        int byteread = 0;
        File oldfile = new File(oldPath);
        if (oldfile.exists()) { //文件存在时
            InputStream inStream = new FileInputStream(oldPath); //读入原文件
            FileOutputStream fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[1444];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread; //字节数 文件大小
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
        }
    }

    /**
     * 删除文件
     *
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @return boolean
     */
    private void delFile(String filePathAndName) throws Exception {
        String filePath = filePathAndName;
        filePath = filePath.toString();
        java.io.File myDelFile = new java.io.File(filePath);
        myDelFile.delete();
    }

    /**
     * 移动文件到指定目录
     *
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    private void moveFile(String oldPath, String newPath) throws Exception {
        copyFile(oldPath, newPath);
        delFile(oldPath);
        dataBase.log("文件移动成功：" + oldPath + "===>" + newPath);
    }

    private void dealFile(File f1) {

        String ext = f1.getName().substring(f1.getName().lastIndexOf(".") + 1
                , f1.getName().length());

        boolean extFlg = false;
        for (type aa : type.values()) {
            if (aa.name().equals(ext.toLowerCase())) {
                extFlg = true;
                break;
            }
        }

        if (extFlg) {
            String md5s = "";
            String tempOut = "";
            try {
                md5s = MD5Util.md5HashCode(new FileInputStream(f1));
                // md5 重复，已经存在的文件
                // 出log，并且移动到一个统一的目录下
                if (dataBase.isExists(md5s)) {
                    tempOut = dataBase.getRepeatPath() + File.separator + f1.getName() + System.currentTimeMillis() + "." + ext;
                    String fds = tempOut.substring(0, tempOut.lastIndexOf(File.separator));
                    File f3 = new File(fds);
                    if (!f3.exists()) {
                        f3.mkdirs();
                    }
                    dataBase.log("文件： [" + f1.getName() + "] 重复，已经移动到：【" + tempOut + "】");
                    moveFile(f1.getAbsolutePath(), tempOut);
                    // md5 不重复，合并文件夹
                } else {
                    Date dateString = readPicDate(f1, ext);
                    if (dateString != null) {
                        tempOut = combineOutPath(dateString) + "." + ext;
                        String fds = tempOut.substring(0, tempOut.lastIndexOf(File.separator));
                        File f3 = new File(fds);
                        if (!f3.exists()) {
                            f3.mkdirs();
                        }
                        dataBase.add(md5s, new ImageFile( md5s,tempOut));
                        moveFile(f1.getAbsolutePath(), tempOut);
                    } else {
                        dataBase.log("文件： [" + f1.getName() + "] 没有时间属性，没办法整理，请手动整理。");
                    }
                }
            } catch (Exception e) {
                dataBase.remove(md5s);
                dataBase.log("文件移动失败：" + f1.getAbsolutePath() + "===>" + tempOut);
            }
        } else {
            dataBase.log("不支持的文件格式" + f1.getName() + "] 现在支持PNG,jpeg。");
        }
    }


    enum type {
        jpg, png
    }
}
