package com.example.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.BlogTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BlogTag)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-24 13:37:48
 */
@Mapper
public interface BlogTagDao extends BaseMapper<BlogTag> {

}

