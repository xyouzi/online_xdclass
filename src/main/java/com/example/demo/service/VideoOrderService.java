package com.example.demo.service;

import com.example.demo.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {
    int save(int userId,int videoId);

    List<VideoOrder> listOrderByUserId(Integer userId);
}
