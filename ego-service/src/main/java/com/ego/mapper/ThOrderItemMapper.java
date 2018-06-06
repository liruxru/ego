package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ego.mapper.po.ThOrderItem;
import com.ego.mapper.po.relationPo.ThandUserGood;

public interface ThOrderItemMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ThOrderItem record);

	int insertSelective(ThOrderItem record);

	ThOrderItem selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ThOrderItem record);

	int updateByPrimaryKey(ThOrderItem record);

	@Select("select count(id) from thorderitems")
	int countAllOrderItems();
	  /**
     *  查看未审核的总数量
	 * @param shenhestatus 
     * @param offset
     * @param length
     * @return {@link Integer}
     */
	@Select("select count(id) from thorderitems where shenhestatus =#{shenhestatus}")
	int count(Integer shenhestatus);
	
  
	@Select("select * from thorderitems tho order by tho.creadedate asc limit #{1},#{2}" )
	List<ThOrderItem> selectAllOrderItems(int offset, int length);

	@Select("select * from thorderitems tho order by tho.creadedate asc where tho.user=#{0} limit #{1},#{2}")
	List<ThOrderItem> selectAllOrderItemsByUserId(int userId, int offset, int length);
	@Select("select * from thorderitems where snorders=#{0}")
	ThOrderItem selectThBySnOrders(String snorders);
	
	/**
     * 根据审核状态来查询的
     * @param offset
     * @param length
     * @author 秦健
     */
	@Select("select th.id as id,th.sn as sn,th.goods as goods,th.snorders as snorders,th.yuanyin as yuanyin,th.creadedate as creadedate ,"
			+ " th.status as status,th.store as store,th.user as user,u.username as username  from thorderitems th"
			+ " left join stores s on(th.store = s.id) \r\n"  
			+ " left join users u on(u.id=th.user) where shenhestatus = #{0} limit #{1},#{2}")
	List<ThandUserGood> selectByShenhestatus(Integer shenhestatus, int offset, int length);
	
}