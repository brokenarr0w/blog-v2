package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.dao.CategoryDao;
import com.example.backend.entity.Category;
import com.example.backend.service.CategoryService;
import com.example.backend.utils.R;
import org.springframework.stereotype.Service;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2023-10-18 21:04:13
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

    @Override
    public R<Page<Category>> getCategoryListByPageNum(Long page, Long pageNum) {
        Page<Category> categoryPage = new Page<>(page,pageNum);
        this.page(categoryPage);
        return R.success("查询成功",categoryPage);
    }



}

