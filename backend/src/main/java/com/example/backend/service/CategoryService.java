package com.example.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.Category;
import com.example.backend.utils.R;

/**
 * (Category)表服务接口
 *
 * @author makejava
 * @since 2023-10-18 21:04:13
 */
public interface CategoryService extends IService<Category> {
    R<Page<Category>> getCategoryListByPageNum(Long page,Long pageNum);
}

