package com.example.demo.service.impl;

import com.example.demo.model.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.CommonUtils;
import com.example.demo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(Map<String, String> userInfo) {
        User user = parseToUser(userInfo);
        if(user != null){
            return userMapper.save(user);
        }else {
            return -1;
        }
    }

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user = userMapper.findByPhoneAndPwd(phone,CommonUtils.MD5(pwd));
        if(user == null){
            return null;
        }
        else{
            String token = JWTUtils.geneJsonWebToken(user);
            return token;
        }
    }

    @Override
    public User findByUserId(Integer userId) {
        User user = userMapper.findByUserId(userId);
//        user.setPwd("");
        return user;
    }

    /**
     * 解析user对象
     * @param userInfo
     * @return
     */
    private User parseToUser(Map<String,String> userInfo){
        if(userInfo.containsKey("phone") && userInfo.containsKey("pwd") && userInfo.containsKey("name")){
            User user = new User();
            user.setName(userInfo.get("name"));
            user.setHeadImg(getRandomImg());
            user.setCreateTime(new Date());
            user.setPhone(userInfo.get("phone"));
            String pwd = userInfo.get("pwd");
            user.setPwd(CommonUtils.MD5(pwd));
            return user;
        }else {
            return null;
        }
    }

//    随机头像
    private static final String [] headImg = {
        "/static/imgs/hlw1.jpg",
        "/static/imgs/hlw2.jpg",
        "/static/imgs/hlw3.jpg",
        "/static/imgs/hlw4.jpg",
        "/static/imgs/hlw5.jpg",
        "/static/imgs/hlw6.jpg",
        "/static/imgs/hlw7.jpg",
    };

    private String getRandomImg(){
        int size = headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }
}
