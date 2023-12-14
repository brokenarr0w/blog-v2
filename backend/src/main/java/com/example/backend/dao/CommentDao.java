package com.example.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-22 11:37:16
 */
@Mapper
public interface CommentDao extends BaseMapper<Comment> {
}

