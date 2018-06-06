package com.ego.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.ego.document.Description;
import com.ego.document.PingJia;
import com.ego.document.po.PingJiaWithDescription;

public class DescriptionConvertUtil {
	public static List<PingJiaWithDescription> convert(List<PingJia> pingJiaList){
		List<PingJiaWithDescription> pingJiaWithDescriptionList=new ArrayList<PingJiaWithDescription>();
		for (PingJia pingJia : pingJiaList) {
			//new 一个 PingJiaWithDescription
			PingJiaWithDescription  PingJiaWithDescription=new PingJiaWithDescription();
			//复制他的属性  然后就剩下LinkedHashMap<Description,Description> pin_hui_Entiy;//评价和回复的实体    没有值
			BeanUtils.copyProperties(pingJia, PingJiaWithDescription);
			LinkedHashMap<String, String> pin_hui = pingJia.getPin_hui();
			LinkedHashMap<Description,Description> pin_hui_Entiy=new LinkedHashMap<Description,Description>();
			//遍历评价的 LinkedHashMap<String, String> pin_hui  把他装换成实体Description
			Set<Entry<String, String>> entrySet = pin_hui.entrySet();
			for (Entry<String, String> entry : entrySet) {
				Description descriptionUser=null;
				Description descriptionAdmin=null;
				String key = entry.getKey();
				String value = entry.getValue();
				if(key!=null) {
					descriptionUser=stringToDescription(key);
				}
				if(value!=null) {
					descriptionAdmin=stringToDescription(value);
				}
				pin_hui_Entiy.put(descriptionUser, descriptionAdmin);
			}
			PingJiaWithDescription.setPin_hui_Entiy(pin_hui_Entiy);
			pingJiaWithDescriptionList.add(PingJiaWithDescription);
		}
		return pingJiaWithDescriptionList;
	}
	
	private static Description stringToDescription(String text) {
		String[] filed = text.split(",");
		String _id=filed[0].substring( filed[0].indexOf("=")+1, filed[0].length());
		String txt=filed[1].substring( filed[1].indexOf("=")+1, filed[1].length());
		String date=filed[2].substring( filed[2].indexOf("=")+1, filed[2].length());
		Date createdate=new Date(Long.valueOf(date));
		Description description=new Description(_id, txt, createdate);
		return description;
	}
}
