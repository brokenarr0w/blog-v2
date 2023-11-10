package com.example.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.PersonCard;
import org.apache.ibatis.annotations.Mapper;

/**
 * (PersonCard)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-25 16:10:54
 */
@Mapper
public interface PersonCardDao extends BaseMapper<PersonCard> {

}

