package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.constants.DatabaseConstant;
import com.example.backend.entity.Comment;
import com.example.backend.dao.CommentDao;
import com.example.backend.service.CommentService;
import com.example.backend.utils.R;
import org.springframework.stereotype.Service;
/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-11-22 11:37:16
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao,Comment> implements CommentService {


    @Override
    public Page<Comment> getCommentListByPage(Long page, Long pageSize, Integer type, Long blogId) {

        Page<Comment> commentPage = new Page<>(page,pageSize);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(DatabaseConstant.CommentTable.COLUMN_TYPE,type)
                .eq(type==1,DatabaseConstant.CommentTable.COLUMN_BLOGID,blogId);
        this.page(commentPage,queryWrapper);
        return commentPage;
    }

    @Override
    public R<Object> uploadComment(Comment reqDto) {
        save(reqDto);
        return R.success("发布评论成功");
    }
}
