package com.example.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.WebStats;
import org.apache.ibatis.annotations.Mapper;

/**
 * (WebStats)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-30 19:30:38
 */
@Mapper
public interface WebStatsDao extends BaseMapper<WebStats> {

}

