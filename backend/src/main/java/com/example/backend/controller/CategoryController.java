package com.example.backend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Blog;
import com.example.backend.entity.Category;
import com.example.backend.service.BlogService;
import com.example.backend.service.CategoryService;
import com.example.backend.utils.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Category)表控制层
 *
 * @author makejava
 * @since 2023-10-18 21:04:12
 */
@RestController
@RequestMapping("/category")
@CrossOrigin
@Slf4j
public class CategoryController{
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;
    @Resource
    private BlogService blogService;
    @GetMapping("/list")
    public R<List<Category>> list(){
        return R.success("查询成功",categoryService.list());
    }
    @GetMapping("/list/page")
    public R<Page<Category>> page(Long page, Long pageNum){
        return categoryService.getCategoryListByPageNum(page,pageNum);
    }
    @PostMapping
    @SaCheckLogin
    public R saveCategory(@RequestBody JSONObject category){
        JSONObject data = (JSONObject) category.get("data");
        Category bean = JSONUtil.toBean(data, Category.class);
        categoryService.saveOrUpdate(bean);
        return R.success("保存成功");
    }
    @GetMapping("/count")
    public R<Long> count(){
        return R.success("查询成功",categoryService.count());
    }
    @DeleteMapping
    @SaCheckLogin
    public R<Object> delete(Long id){
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getCategoryId,id);
        List<Blog> list = blogService.list(wrapper);
        if(!list.isEmpty()){
            blogService.removeByIds(list);
        }
        categoryService.removeById(id);
        return R.success("你丢失了很多东西");
    }
    @GetMapping
    public R<Category> get(Long id){
        return R.success("查询成功",categoryService.getById(id));
    }
}

