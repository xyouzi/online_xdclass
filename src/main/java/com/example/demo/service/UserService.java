package com.example.demo.service;

import com.example.demo.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 新增用户
 */
public interface UserService {
    int save(Map<String,String> userInfo);

    String findByPhoneAndPwd(@Param("phone") String phone,@Param("pwd") String pwd);

    User findByUserId(@Param("user_id") Integer userId);
}
