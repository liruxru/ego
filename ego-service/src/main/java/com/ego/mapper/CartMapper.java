package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Cart;
@Repository("cartMapper")
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
    @Select("select * from cart where goods=#{0} and user=#{1}")
	Cart selectCartByGoodsIdAndUserId(String goodsId, Integer userId);
    @Select("select count(id) from cart where user=#{userId} ")
	int countUserCart(Integer userId);
    @Select("select * from cart where user=#{0} "
    		+ "order by createdate "
    		+ "limit #{1},#{2}")
	List<Cart> selectCartByUser(Integer userId, int offset, int length);
    @Update("update cart  set  num=#{num} where id=#{id}")
	void updateCartNumByPrimaryKey(Cart cart);

	void deleteByCartIds(Integer[] cartIds);

	List<Cart> selectByCartIds(Integer[] cartIds);
}