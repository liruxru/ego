package com.ego.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Order;
import com.ego.mapper.po.relationPo.OrderWithOrderItem;
@Repository("orderMapper")
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);
    
    @Select("select sum(items.num * items.price) from  orders o left join "
    		+" orderitems items on(o.id=items.orders) where o.status=11")
    BigDecimal selectCountMoney();

    int insertSelective(Order record);
    @Select("select * from orders where id=#{id}")
    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);
    
    @Select("select count(id) from orders where status=#{status}")
    int countOrderByStatus(Integer status);
    
    int updateByPrimaryKey(Order record);
    @Select("select count(id) from orders where user=#{0}")
	int countOrderByUserId(Integer userId);
    @Select("select * from orders where user=#{0} limit #{1},#{2}")
	List<Order> selectOrdersByUserId(Integer userId, int offset, int length);
    @Update("update orders o set o.status=#{1} where id=#{0}")
	void updateOrderStatus(Integer orderId, Integer status);
    @Select("select count(id) from orders o where  o.status=#{1} and o.user=#{0} ")
	int countOrdersByUserIdAndStatus(Integer userId, Integer status);
    @Select("select * from orders o where  o.status=#{3} and o.user=#{0} limit #{1},#{2} ")
	List<Order> selectOrdersByUserIdAndStatus(Integer userId, int offset, int length, Integer status);
    @Select("select count(id) from orders o where  o.status=#{0}  ")
	int countOrdersByStatus(Integer status);
    @Select("select * from orders o where  o.status=#{2} limit #{0},#{1}  ")
	List<Order> selectOrdersByStatus(int offset, int length, Integer status);
    @Select("select count(id) from orders o where  o.user=#{0} and o.createdate > #{1} and o.createdate < #{2}")
	int countOrdersByDate(Integer userId, Date begin, Date end);
    @Select("select * from orders o where  o.user=#{0} and o.createdate > #{1} and o.createdate < #{2} limit #{3},#{4} ")
	List<Order> selectOrdersByDate(Integer userId, Date begin, Date end, int offset, int length);
    
 
    @Select("select o.id as orderId,o.sn as sn, o.user as user ,"
    		+ " o.sum as sum ,o.stores as storesId,"
    		+ " o.status as status,o.createdate as createdate  from orders o "
    		+ "where o.user=#{0} order by o.createdate desc  limit #{1},#{2} ")
	List<OrderWithOrderItem> selectOrderWithOrderItemByUserId(Integer userId, int offset, int length);
    @Select("select o.id as orderId,o.sn as sn, o.user as user ,"
    		+ " o.sum as sum ,o.stores as storesId,"
    		+ " o.status as status,o.createdate as createdate  from orders o "
    		+ "where o.id=#{0} ")
	OrderWithOrderItem selectOrderWithOrderItemByorderId(Integer orderId);
    @Select("select o.id as orderId,o.sn as sn, o.user as user ,"
    		+ " o.sum as sum ,o.stores as storesId,"
    		+ " o.status as status,o.createdate as createdate  from orders o "
    		+ "where o.user=#{0} and o.status=#{1} order by o.createdate desc limit #{2},#{3} ")
	List<OrderWithOrderItem> selectOrderWithOrderItemByUserIdAndStatus(Integer userId, Integer status, int offset,
			int length);
    @Select("select o.id as orderId,o.sn as sn, o.user as user ,"
    		+ " o.sum as sum ,o.stores as storesId,"
    		+ " o.status as status,o.createdate as createdate  from orders o "
    		+ "where o.status=#{0} limit #{1},#{2} ")
	List<OrderWithOrderItem> selectOrderWithOrderItemByStatus(Integer status, int offset, int length);

}