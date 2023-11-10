package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.dao.BlogTagDao;
import com.example.backend.entity.BlogTag;
import com.example.backend.service.BlogTagService;
import org.springframework.stereotype.Service;

/**
 * (BlogTag)表服务实现类
 *
 * @author makejava
 * @since 2023-10-24 13:37:48
 */
@Service("blogTagService")
public class BlogTagServiceImpl extends ServiceImpl<BlogTagDao, BlogTag> implements BlogTagService {

}

