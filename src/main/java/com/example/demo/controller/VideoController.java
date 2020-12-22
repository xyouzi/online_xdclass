package com.example.demo.controller;

import com.example.demo.model.entity.Video;
import com.example.demo.model.entity.VideoBanner;
import com.example.demo.service.VideoService;
import com.example.demo.utils.JsonData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 轮播图列表
     * @return
     */
    @GetMapping("list_banner")
    public JsonData indexBanner(){
        List<VideoBanner> bannerList = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);
    }

    /**
     * 视频列表
     * @return
     */
    @RequestMapping("list")
    public Object listVideo(){
        List<Video> videoList = videoService.listVideo();
        return JsonData.buildSuccess(videoList);
    }

    /**
     * 分页加载视频列表
     * @return
     */
    @GetMapping("limit")
    public Object listVideoLimit(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "6") int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Video> pageInfo = new PageInfo<>(videoService.getUsers());
        HashMap<String, Object> map = new HashMap<>();
//        总数
        map.put("total",pageInfo.getTotal());
//        当前页码
        map.put("currentNum",pageInfo.getPageNum());
//        每页条数
        map.put("pageSize",pageInfo.getPageSize());
        List<Video> list = pageInfo.getList();
//        分页数据
        map.put("list",list);

        return JsonData.buildSuccess(map);
    }

    /**
     * 查询视频详情，包含章集
     * @param videoId
     * @return
     */
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true)int videoId){
        Video video = videoService.findDetailById(videoId);
        return JsonData.buildSuccess(video);
    }
}
