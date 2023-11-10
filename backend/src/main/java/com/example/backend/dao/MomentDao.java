package com.example.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Moment;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Moment)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-25 15:18:46
 */
@Mapper
public interface MomentDao extends BaseMapper<Moment> {

}

