package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.Tag;

import java.util.List;

/**
 * (Tag)表服务接口
 *
 * @author makejava
 * @since 2023-10-18 21:07:19
 */
public interface TagService extends IService<Tag> {
    List<Tag> getListByIds(List<Integer> idList);
}

