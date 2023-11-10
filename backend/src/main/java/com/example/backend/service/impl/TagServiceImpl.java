package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.dao.TagDao;
import com.example.backend.entity.Tag;
import com.example.backend.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-10-18 21:07:19
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagDao, Tag> implements TagService {

    @Override
    public List<Tag> getListByIds(List<Integer> idList) {
        return null;
    }
}

