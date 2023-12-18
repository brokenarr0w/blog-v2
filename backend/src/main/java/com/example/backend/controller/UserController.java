package com.example.backend.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2023-10-18 21:06:38
 */
@RestController
@CrossOrigin
@Slf4j
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    public UserService userService;
    @PostMapping("/login")
    public R<Object> login(@RequestBody JSONObject data){
        Object o = data.get("data");
        JSONObject entries = JSONUtil.parseObj(o);
        String username = entries.getStr("username");
        String password = entries.getStr("password");
        String md5 = SecureUtil.md5(password);
        User user = userService.getOne(null);
        if(!user.getUsername().equals(username)){
            return R.failure(R.USER_NOT_FOUND,"用户不存在");
        }
        if(!user.getPassword().equals(md5)){
            return R.failure(R.PASSWORD_ERROR,"密码错误");
        }
        StpUtil.login(username);
        return R.success("登录成功",StpUtil.getTokenValue());
    }
    @GetMapping("/login")
    public R<Boolean> checkLogin(){
        boolean login = StpUtil.isLogin();
        return R.success("检查完成",login);
    }
}

