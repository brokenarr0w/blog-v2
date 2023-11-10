package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.dao.AboutDao;
import com.example.backend.entity.About;
import com.example.backend.service.AboutService;
import org.springframework.stereotype.Service;

/**
 * (About)表服务实现类
 *
 * @author makejava
 * @since 2023-10-18 20:53:55
 */
@Service("aboutService")
public class AboutServiceImpl extends ServiceImpl<AboutDao, About> implements AboutService {

}

