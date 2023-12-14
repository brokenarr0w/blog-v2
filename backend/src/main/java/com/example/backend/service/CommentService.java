package com.example.backend.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.Comment;
import com.example.backend.utils.R;

/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2023-11-22 11:38:46
 */
public interface CommentService extends IService<Comment> {
    Page<Comment> getCommentListByPage(Long page, Long pageSize, Integer type, Long blogId);

    R<Object> uploadComment(Comment reqDto);
}
