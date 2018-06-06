package com.ego.service;

import com.ego.document.Goods;
import com.ego.document.LiuLan;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;
import com.ego.utils.SearchLiulan;

/**
 * 用户浏览足迹
 * 
 * @author Administrator
 *
 */
public interface LiuLanService {
	/**
	 * 通过用户id查看用户浏览足迹
	 * 
	 * @param userId
	 * @return
	 */
	PageBean<LiuLan> selectLiuLanByUserId(SearchLiulan searchLiulan);

	/***
	 * 通过用户id 插入浏览信息
	 * @param searchLiulan
	 * @return
	 */
	ReturnMessage<LiuLan> insertLiulanByUserId(String userid,Goods goods);
	/**
	 *  通过用户id 删除浏览信息
	 * @param string
	 * @param goods
	 * @return
	 */
	ReturnMessage<LiuLan> deleteLiuLanByUserId(String string, Goods goods);
}
