package com.example.backend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.backend.entity.PersonCard;
import com.example.backend.entity.Setting;
import com.example.backend.service.PersonCardService;
import com.example.backend.service.SettingService;
import com.example.backend.utils.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * (Setting)表控制层
 *
 * @author makejava
 * @since 2023-10-25 16:08:41
 */
@RestController
@RequestMapping("setting")
@CrossOrigin
@Slf4j
public class SettingController {
    /**
     * 服务对象
     */
    @Resource
    private SettingService settingService;
    @Resource
    private PersonCardService personCardService;
    @GetMapping("/card")
    public R<PersonCard> getCard(){
        return R.success("查询成功",personCardService.query().one());
    }
    @GetMapping
    public R<Setting> getSetting(){
        return R.success("查询成功",settingService.query().one());
    }
    @PostMapping
    @SaCheckLogin
    public R<Object> update(@RequestBody JSONObject data){
        Object card = data.get("card");
        Object setting = data.get("setting");
        PersonCard cardBean = JSONUtil.toBean(JSONUtil.parseObj(card), PersonCard.class);
        Setting settingBean = JSONUtil.toBean(JSONUtil.parseObj(setting), Setting.class);
        settingService.saveOrUpdate(settingBean);
        personCardService.saveOrUpdate(cardBean);
        return R.success("你对自己的定义已经更新");
    }
}

