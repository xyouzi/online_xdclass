package com.example.demo.service.impl;

import com.example.demo.config.CacheKeyManager;
import com.example.demo.model.entity.Video;
import com.example.demo.model.entity.VideoBanner;
import com.example.demo.mapper.VideoMapper;
import com.example.demo.service.VideoService;
import com.example.demo.utils.BaseCache;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> listVideo() {
        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST,()->{
                List<Video> videoList = videoMapper.listVideo();
                return videoList;
            });
            if(cacheObj instanceof List){
                List<Video> videoList = (List<Video>) cacheObj;
                return videoList;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<VideoBanner> listBanner() {
//        try{
//            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY,()->{
        List<VideoBanner> bannerList = videoMapper.listVideoBanner();
        return bannerList;
//            });
//            if(cacheObj instanceof List){
//                List<VideoBanner> bannerList = (List<VideoBanner>)cacheObj;
//                return bannerList;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
    }

    @Override
    public Video findDetailById(int videoId) {

        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL, videoId);
        try {
            Object cacheObj = baseCache.getOneHourCache().get(videoCacheKey,()->{
                Video video = videoMapper.findDetailById(videoId);
                return video;
            });
            if(cacheObj instanceof Video){
                Video video = (Video)cacheObj;
                return video;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Page<Video> getUsers() {
        return videoMapper.listVideoLimit();
    }


}
