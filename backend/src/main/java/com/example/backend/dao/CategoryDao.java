package com.example.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-18 21:04:12
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

}

