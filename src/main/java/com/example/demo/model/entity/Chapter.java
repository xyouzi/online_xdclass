package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

//章对象
public class Chapter {
    private Integer id;
    @JsonProperty("video_id")
    private Integer videoId;
    private String title;
    private Integer ordered;
    @JsonProperty("create_time")
    private Date createTime;

    @JsonProperty("episode_list")
    private List<Episode> episodeList;

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", videoId=" + videoId +
                ", title='" + title + '\'' +
                ", ordered=" + ordered +
                ", createTime=" + createTime +
                ", episodeList=" + episodeList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }
}
