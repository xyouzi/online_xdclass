package com.example.demo.mapper;

import com.example.demo.model.entity.Episode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeMapper {
    Episode findFirstEpisodeById(@Param("video_id") int videoId);
}
