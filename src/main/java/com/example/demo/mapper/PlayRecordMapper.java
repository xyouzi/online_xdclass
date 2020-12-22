package com.example.demo.mapper;

import com.example.demo.model.entity.Episode;
import com.example.demo.model.entity.PlayRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRecordMapper {
    int saveRecord(PlayRecord playRecord);
}
