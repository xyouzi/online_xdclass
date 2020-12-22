package com.example.demo.service;

import com.example.demo.model.entity.Video;
import com.example.demo.model.entity.VideoBanner;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

public interface VideoService {
    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);

    Page<Video> getUsers();
}
