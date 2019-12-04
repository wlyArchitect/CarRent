package com.wh.sys.task;

import java.io.File;

import com.wh.sys.constant.SysConstant;
import com.wh.sys.utils.AppFileUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时扫描_temp图片进行清理
 * 为什么需要这个一个清理的???
 * 如果进行添加操作时,只是选择了图片未进行保存,会生成一个临时_temp文件
 *
 * @EnableScheduling 开启定时任务
 * @author wh
 */
@Component
@EnableScheduling
public class RecycleTempFileTask {
	/**
	 * 每天晚上12点执行
	 */
	@Scheduled(cron="0 0 0 * * ? ")
	public void recyleTempFile() {
		//先获取根目录,拿到所有的文件夹
		File file=new File(AppFileUtils.PATH);
		deleteFile(file);
	}
	
	/**
	 * 删除图片
	 * 递归
	 * @param file
	 */
	public void deleteFile(File file) {
		if(null!=file) {
			File[] listFiles = file.listFiles();
			if(null!=listFiles&&listFiles.length>0) {
				//递归删除_temp文件
				for (File f : listFiles) {
					if(f.isFile()) {
						if(f.getName().endsWith(SysConstant.FILE_UPLOAD_TEMP)) {
							f.delete();
						}
					}else {
						//如果是文件夹，再递归删除
						deleteFile(f);
					}
				}
			}
		}
	}
}
