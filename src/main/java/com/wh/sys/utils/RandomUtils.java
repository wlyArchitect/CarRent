package com.wh.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机数工具类(还未使用)
 * @author 万浩
 * @data 2019/11/15 19:36
 * @description
 */
public class RandomUtils {
    private static SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat sdf3=new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
    private static Random random=new Random();

    /**
     * 得到当前日期
     */
    public static String getCurrentDateForString() {
        return sdf1.format(new Date());
    }


    /**
     * 生成文件名使用时间+4位随机数
     * @param
     */
    public static String createFileNameUseTime(String fileName) {
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."),fileName.length());
        String time=sdf2.format(new Date());
        Integer num=random.nextInt(9000)+1000;
        return time+num+fileSuffix;
    }

    /**
     * 生成文件名使用时间+4位随机数
     * 并且添加一个临时文件后缀_temp
     * @param fileName 文件名称
     * @param suffix   临时文件后缀_temp
     */
    public static String createFileNameUseTime(String fileName,String suffix) {
        //取出文件的后缀名
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."),fileName.length());
        String time=sdf2.format(new Date());
        Integer num=random.nextInt(9000)+1000;
        return time+num+fileSuffix+suffix;
    }

    /**
     * 生成文件名使用UUID
     * @param
     */
    public static String createFileNameUseUUID(String fileName) {
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."),fileName.length());
        return UUID.randomUUID().toString().replace("-", "").toUpperCase()+fileSuffix;
    }
    /**
     * 根据时间+五位随机数成字符串
     * @param preffx
     * @return
     */
    public static String createRandomStringUseTime(String preffx) {
        return preffx+"_"+sdf3.format(new Date())+"_"+(random.nextInt(90000)+10000);
    }

}
