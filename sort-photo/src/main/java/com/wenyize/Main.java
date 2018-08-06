package com.wenyize;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

/**
 *
 * 1.整理图片文件
 * 2.清除重复文件
 *   重复文件采用hash算法做比较
 *   为了减少后续的操作，采用一种追加的操作
 *   即建立一个基本集，该基本集以一个文件索引为基准
 *   （这个文件索引应该具有校验功能）
 *   （可以建立新规则，把图片重新排序组合）
 *   后面新来的图片文件追加到前面的基准集中
 * 3.做成一个命令行工具
 *
 *   //https://blog.csdn.net/u013752202/article/details/78568995
 *
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

    static String[] IN_FOLUDAR = properties.getProperty("input").split(";");

    static String OUT_FOluDAR =  properties.getProperty("output");

    public static void main(String[] args) {

        for (String tempF : IN_FOLUDAR) {
            File temp2 = new File(tempF);
            File[] temps = temp2.listFiles();

            for (File temp3 : temps) {

                if (temp3.isFile()) {
                    if (temp3.getName().substring(temp3.getName().lastIndexOf(".") + 1, temp3.getName().length()).equals("JPG")) {
                        Date dateString = readPic(temp3);

                        if (dateString != null) {
                            String tempOut = combineOutPath(dateString);

                            String fds = tempOut.substring(0, tempOut.lastIndexOf("\\"));

                            File fffg = new File(fds);
                            if (!fffg.exists()) {
                                fffg.mkdirs();
                            }

                            moveFile(temp3.getAbsolutePath(), tempOut);
                        }

                    }

                }
            }
        }
    }

    public static String combineOutPath(Date date) {
        StringBuilder sb = new StringBuilder();
        sb.append(OUT_FOluDAR);

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        sb.append(ca.get(Calendar.YEAR) + "\\");
        sb.append((ca.get(Calendar.MONTH) + 1) + "\\");

        int max = getMaxNum(ca);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        sb.append(df.format(date));
        sb.append("-");
        sb.append(String.format("%04d", max + 1));
        sb.append(".JPG");
        return sb.toString();
    }

    public static int getMaxNum(Calendar date) {
        int max = 0;
        File fff = new File(OUT_FOluDAR + date.get(Calendar.YEAR) + "\\" + (date.get(Calendar.MONTH) + 1));
        if (fff.exists()) {
            File[] ffs = fff.listFiles();
            for (File temp : ffs) {
                int tk = Integer.valueOf(temp.getName().substring(temp.getName().lastIndexOf("-") + 1, temp.getName().lastIndexOf(".")));
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
    private static Date readPic(File jpegFile) {
        //= new File("G:\\photo\\新建文件夹 (2)\\170414.35.JPG");
        Metadata metadata;
        Date result = null;
        boolean timeExistFlg = false;
        try {
            metadata = JpegMetadataReader.readMetadata(jpegFile);
            Iterator<Directory> it = metadata.getDirectories().iterator();
            while (it.hasNext()) {
                Directory exif = it.next();
                Iterator<Tag> tags = exif.getTags().iterator();
                while (tags.hasNext()) {
                    Tag tag = (Tag) tags.next();
                    if (tag.toString().contains("Date/Time Original")) {
                        String[] temp = tag.toString().split("-");
                        DateFormat dateFormat = new SimpleDateFormat(" yyyy:MM:dd HH:mm:ss");
                        try {
                            result = dateFormat.parse(temp[1]);
                            timeExistFlg = true;
                        } catch (ParseException e) {
                            System.out.println(jpegFile.getName() + ": 时间转换错误");
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (!timeExistFlg) {
                System.out.println(jpegFile.getName() + ": 该图片没有拍摄时间");
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
    public static void copyFile(String oldPath, String newPath) {
        try {
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
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @return boolean
     */
    public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 移动文件到指定目录
     *
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);
        System.out.println("文件移动成功：" + oldPath + "===>" + newPath);
    }
}
