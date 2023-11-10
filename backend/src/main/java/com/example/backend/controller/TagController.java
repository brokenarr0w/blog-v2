package com.example.backend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Tag;
import com.example.backend.service.TagService;
import com.example.backend.utils.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Tag)表控制层
 *
 * @author makejava
 * @since 2023-10-18 21:07:19
 */
@RestController
@RequestMapping("/tag")
@CrossOrigin
@Slf4j
public class TagController{
    /**
     * 服务对象
     */
    @Resource
    private TagService tagService;
    @GetMapping("/list")
    public R<List<Tag>> list(){
        return R.success("查询成功",tagService.list());
    }
    @GetMapping("/page")
    public R<Page<Tag>> page(Long page,Long pageNum){
        Page<Tag> tagPage = new Page<>(page,pageNum);
        tagService.page(tagPage);
        return R.success("查询成功",tagPage);
    }
    @PostMapping
    @SaCheckLogin
    public R<Object> save(@RequestBody JSONObject data){
        Object object = data.get("data");
        Tag bean = JSONUtil.toBean(JSONUtil.parseObj(object), Tag.class);
        log.info(bean.toString());
        tagService.saveOrUpdate(bean);
        return R.success("保存成功（或许贴标签不是个好主意）");
    }
    @GetMapping("/count")
    public R<Long> count(){
        return R.success("查询成功",tagService.count());
    }
    @DeleteMapping
    @SaCheckLogin
    public R<Object> delete(Long id){
        tagService.removeById(id);
        return R.success("你去除了一个标签");
    }
    @GetMapping
    public R<Tag> getTagId(Long id){
        return R.success("查询成功",tagService.getById(id));
    }
}

