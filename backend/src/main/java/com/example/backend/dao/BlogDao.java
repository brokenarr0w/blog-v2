package com.example.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Blog)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-18 20:59:05
 */
@Mapper
public interface BlogDao extends BaseMapper<Blog> {

}

