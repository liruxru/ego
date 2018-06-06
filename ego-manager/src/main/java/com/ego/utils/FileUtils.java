package com.ego.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtils {
	
	public static String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."), fileName.length());
		
	}
	
	//生成新的文件名称
	public static String getFileSn() {
		Date d=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		return "F"+simpleDateFormat.format(d)+UUID.randomUUID().toString().replaceAll("-", "");
	}

}
