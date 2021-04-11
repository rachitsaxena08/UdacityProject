package com.example.userAuthentication.mapper;

import com.example.userAuthentication.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM WHERE {username}=#{username}")
    User getUser(String username);

    @Insert("INSERT INTO Users(username,salt,password,firstName,lastName) VALUES(#{username},#{salt},#{password},#{firstName},#{lastName}")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
}
