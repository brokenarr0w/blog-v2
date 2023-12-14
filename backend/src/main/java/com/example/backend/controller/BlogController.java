package com.example.backend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.dto.BlogDto;
import com.example.backend.entity.Blog;
import com.example.backend.entity.BlogTag;
import com.example.backend.service.BlogService;
import com.example.backend.service.BlogTagService;
import com.example.backend.utils.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Blog)表控制层
 *
 * @author makejava
 * @since 2023-10-18 20:59:05
 */
@RestController
@RequestMapping("/blog")
@CrossOrigin
@Slf4j
public class BlogController {
    /**
     * 服务对象
     */
    @Resource
    private BlogService blogService;
    @Resource
    private BlogTagService blogTagService;
    @GetMapping("/list")
    public R<Page<BlogDto>> list(Long page, Long pageNum, String name){
        return blogService.getBlogListByPageNum(page,pageNum,name);
    }
    @GetMapping
    public R<BlogDto> getById(Long id){
        return blogService.getBlogById(id);
    }
    @PostMapping
    @SaCheckLogin
    public R saveBlog(@RequestBody String blogDto){
        blogService.saveOrUpdateBlog(blogDto);
        return R.success("操作成功");
    }
    @GetMapping("/category")
    public R<Page<BlogDto>> category(Long id,Long page,Long pageNum){
       return blogService.getBlogByCategoryId(id,page,pageNum);
    }
    @GetMapping("/count")
    public R<Long> count(){
        return R.success("查询成功",blogService.count());
    }
    @DeleteMapping
    @SaCheckLogin
    public R<Object> delete(Long blog){
        blogService.removeById(blog);
        return R.success("这是毁灭世界的第一步");
    }
    @GetMapping("/category/count")
    public R<Long> countByCategory(Long id){
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getCategoryId,id);
        return R.success("查询成功",blogService.count(wrapper));
    }
    @GetMapping("/tag")
    public R<Page<BlogDto>> getBlogListByTag(Long id,Long page,Long pageNum){
        return blogService.getBlogByTagId(id,page,pageNum);
    }
    @GetMapping("/tag/count")
    public R<Long> countByTag(Long id){
        LambdaQueryWrapper<BlogTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogTag::getTagId,id);
        return R.success("查询成功",blogTagService.count(wrapper));
    }
    @GetMapping("/all")
    public R<List<Blog>> getBlogList(){
       return R.success("查询成功", blogService.query().orderByDesc("create_time").list());
    }
    @GetMapping("/top")
    public R<List<Blog>> getBlogByIsTop(){
        return R.success("查询成功",blogService.query()
                .orderByDesc("create_time").eq("is_top",1).last("limit 5")
                .list());
    }
    @GetMapping("/prev")
    public R<Blog> getPrevById(@RequestParam("id") String id){
        return blogService.getPrevById(id);
    }
    @GetMapping("/next")
    public R<Blog> getNextById(@RequestParam("id") String id){
        return blogService.getNextById(id);
    }
}

