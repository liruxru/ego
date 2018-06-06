package com.ego.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成编号的工具类
 */
public class SnUtils {
	//生成订单编号
	public static String getOrderSn() {
		Date d=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		return "O"+simpleDateFormat.format(d);
	}
	//生成退单编号
	public static String getThordersSn() {
		Date d=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		return "T"+simpleDateFormat.format(d);
	}
	
}
