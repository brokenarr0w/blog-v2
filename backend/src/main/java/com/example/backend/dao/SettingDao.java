package com.example.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Setting;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Setting)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-25 16:08:41
 */
@Mapper
public interface SettingDao extends BaseMapper<Setting> {

}

