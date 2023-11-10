package com.example.backend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.example.backend.entity.WebStats;
import com.example.backend.service.BlogService;
import com.example.backend.service.WebStatsService;
import com.example.backend.utils.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * (WebStats)表控制层
 *
 * @author makejava
 * @since 2023-10-30 19:31:16
 */
@RestController
@RequestMapping("/view")
@CrossOrigin
public class WebStatsController  {
    /**
     * 服务对象
     */
    @Resource
    private WebStatsService webStatsService;
    @Resource
    private BlogService blogService;
    @SaCheckLogin
    @GetMapping
    public R<Object> get(){
        return R.success("查询成功",webStatsService.getOne(null));
    }
    @PostMapping
    public R<Object> view(){
        WebStats one = webStatsService.getOne(null);
        one.setViews(one.getViews()+1);
        one.setBlogCount(Math.toIntExact(blogService.count()));
        webStatsService.saveOrUpdate(one);
        return R.success("操作成功");
    }

}

