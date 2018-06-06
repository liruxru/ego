package com.ego.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ego.document.Goods;
import com.ego.mapper.po.OrderItems;
import com.ego.mapper.po.OrderItemsWithGoods;

public interface OrderItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItems record);

    int insertSelective(OrderItems record);

    OrderItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItems record);

    int updateByPrimaryKey(OrderItems record);
 
    @Select("select oi.id as id,oi.goods as goods ,oi.num as num,oi.price as price from orderitems oi where oi.orders=#{0} limit #{1},#{2} ")
	List<OrderItemsWithGoods> selectOrderItemsWithGoodsByOrderId(int orderId, int pageCode, int pageSize);
    @Select("select count(id) from orderitems oi where oi.orders=#{0}")
	int countByOrderId(Integer orderId);
    @Delete("delete from orderitems where orders=#{0}")
	void deleteByOrderId(Integer orderId);
}