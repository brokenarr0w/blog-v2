package com.example.backend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.backend.entity.About;
import com.example.backend.service.AboutService;
import com.example.backend.utils.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * (About)表控制层
 *
 * @author makejava
 * @since 2023-10-18 20:53:54
 */
@RestController
@RequestMapping("/about")
@CrossOrigin
@Slf4j
public class AboutController {
    /**
     * 服务对象
     */
    @Resource
    private AboutService aboutService;
    @GetMapping
    public R<About> getAbout(){
      return R.success("查询成功",aboutService.query().one());
    }
    @PostMapping
    @SaCheckLogin
    public R<Object> update(@RequestBody JSONObject about){
        Object object = about.get("about");
        About bean = JSONUtil.toBean(JSONUtil.parseObj(object), About.class);
        aboutService.saveOrUpdate(bean);
        return R.success("您对自我的描述更加清晰");
    }
}

