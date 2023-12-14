package com.example.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.dto.BlogDto;
import com.example.backend.entity.Blog;
import com.example.backend.utils.R;


/**
 * (Blog)表服务接口
 *
 * @author makejava
 * @since 2023-10-18 20:59:05
 */
public interface BlogService extends IService<Blog> {
    R<Page<BlogDto>> getBlogListByPageNum(Long page, Long pageNum, String name);
    R<BlogDto> getBlogById(Long id);
    R saveOrUpdateBlog(String blog);
    R<Page<BlogDto>> getBlogByCategoryId(Long id,Long page,Long pageNum);
    R<Page<BlogDto>> getBlogByTagId(Long id,Long page,Long pageNum);

    R<Blog> getPrevById(String id);

    R<Blog> getNextById(String id);
}

