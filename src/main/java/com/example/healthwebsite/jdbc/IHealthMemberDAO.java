package com.example.healthwebsite.jdbc;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IHealthMemberDAO {
    @Insert("insert into health_member(id, email, password, phone_number) values(#{id},#{email},#{password},#{phoneNumber})")
    public void insert(String id, String email, String password, String phoneNumber);

    @Select("select id from health_member")
    public List<HealthMemberDTO> selectIDList();

    @Select("select password from health_member where id = #{id}")
    public String selectPassword(String id);

}
