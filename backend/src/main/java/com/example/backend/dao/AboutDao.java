package com.example.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.About;
import org.apache.ibatis.annotations.Mapper;
/**
 * (About)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-18 20:53:55
 */
@Mapper
public interface AboutDao extends BaseMapper<About> {

}

