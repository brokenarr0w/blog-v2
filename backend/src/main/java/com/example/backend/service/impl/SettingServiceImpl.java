package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.dao.SettingDao;
import com.example.backend.entity.Setting;
import com.example.backend.service.SettingService;
import org.springframework.stereotype.Service;

/**
 * (Setting)表服务实现类
 *
 * @author makejava
 * @since 2023-10-25 16:08:41
 */
@Service("settingService")
public class SettingServiceImpl extends ServiceImpl<SettingDao, Setting> implements SettingService {

}

