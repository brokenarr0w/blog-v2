package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.dto.requestDto.ReqCommentPageDto;
import com.example.backend.entity.Comment;
import com.example.backend.service.CommentService;
import com.example.backend.utils.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * (Comment)表控制层
 *
 * @author makejava
 * @since 2023-11-22 11:37:16
 */
@RestController
@RequestMapping("comment")
@CrossOrigin
public class CommentController {
    @Resource
    CommentService service;
    @GetMapping("/page")
    public R<Page<Comment>> page(ReqCommentPageDto dto){
        if(dto.getType() != 1 && dto.getType() != 2) {
            return R.failure(403, "传递参数错误");
        }
        return R.success("查询成功",service.getCommentListByPage(dto.getPage(),dto.getPageSize(),dto.getType(),dto.getBlogId()));
    }
    @PostMapping
    public R<Object> post(@RequestBody Comment  reqDto){
        return service.uploadComment(reqDto);
    }
    @GetMapping("/list")
    public R<Page<Comment>> list(@RequestParam Long page,@RequestParam Long pageSize){
        return service.getCommentList(page,pageSize);
    }
}

