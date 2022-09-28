package com.example.healthwebsite.jdbc;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IBuyerInformDAO {
    @Insert("insert into buyer_inform(goods_name, price, buy_date, id) values(#{goodsName}, #{price}, date_format(SYSDATE(), '%Y-%m-%d'), #{id})")
    public void insertBuyerInform(String goodsName, int price, String id);

    @Select("select * from buyer_inform where id = #{id}")
    public List<BuyerInformDTO> selectBuyerList(String id);
}
