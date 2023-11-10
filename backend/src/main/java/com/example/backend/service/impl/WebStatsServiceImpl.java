package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.dao.WebStatsDao;
import com.example.backend.entity.WebStats;
import com.example.backend.service.WebStatsService;
import org.springframework.stereotype.Service;

/**
 * (WebStats)表服务实现类
 *
 * @author makejava
 * @since 2023-10-30 19:30:38
 */
@Service("webStatsService")
public class WebStatsServiceImpl extends ServiceImpl<WebStatsDao, WebStats> implements WebStatsService {

}

