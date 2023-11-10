package com.example.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Tag)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-18 21:07:19
 */
@Mapper
public interface TagDao extends BaseMapper<Tag> {

}

