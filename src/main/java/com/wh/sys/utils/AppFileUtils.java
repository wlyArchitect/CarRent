package com.wh.sys.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * 文件下载
 * @author 万浩
 * @data 2019/11/15 19:27
 * @description
 */
public class AppFileUtils {
    /**
     * 得到文件上传的路径的根目录
     */
    public static String PATH="D:/临时文件/upload/";
    static {
        InputStream stream = AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties=new Properties();
        try {
            properties.load(stream);
            PATH=properties.getProperty("path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件下载
     * @param response
     * @param path
     * @param oldName
     * @return
     */
    public static ResponseEntity<Object> downloadFile(HttpServletResponse response, String path, String oldName) {
        //4,使用绝对路径+相对路径去找到文件对象
        File file=new File(AppFileUtils.PATH,path);
        //5,判断文件是否存在
        if(file.exists()) {
            try {
                try {
                    //如果名字有中文 要处理编码
                    oldName= URLEncoder.encode(oldName, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //把file转成一个bytes
                byte [] bytes= FileUtils.readFileToByteArray(file);
                HttpHeaders header=new HttpHeaders();
                //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
                header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                //设置下载的文件的名称
                header.setContentDispositionFormData("attachment", oldName);
                //创建ResponseEntity对象
                ResponseEntity<Object> entity=
                        new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
                return entity;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }else {
            PrintWriter out;
            try {
                out = response.getWriter();
                out.write("文件不存在");
                out.flush();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 删除车时的操作
     * 根据相对路径删除硬盘上文件
     * @param path
     */
    public static void deleteFileUsePath(String path) {
        String realPath=PATH+path;
        //文件存在就删除
        File file=new File(realPath);
        if(file.exists()) {
            file.delete();
        }
    }

    /**
     * 更改文件名
     * 主要就是去掉后缀_temp
     * @param carimg 子文件夹目录 例如:xxx/xxx.png_temp
     * @param suffix 后缀"_temp"
     */
    public static String updateFileName(String carimg,String suffix) {
        //找到文件
        try {
            File file=new File(PATH,carimg);
            if(file.exists()) {
                //重命名,去掉后缀_temp
                file.renameTo(new File(PATH,carimg.replace(suffix, "")));
                //返回重命名后的子文件夹目录
                return carimg.replace(suffix, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改车时的操作
     * 删除图片
     * @param carimg
     */
    public static void removeFileByPath(String carimg) {
        //判断文件路径
        File file=new File(PATH,carimg);
        if(file.exists()) {
            file.delete();
        }
    }
}
