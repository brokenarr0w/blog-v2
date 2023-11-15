package com.example.backend.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.constants.DatabaseConstant;
import com.example.backend.constants.PageConstant;
import com.example.backend.dao.BlogDao;
import com.example.backend.dto.BlogDto;
import com.example.backend.entity.Blog;
import com.example.backend.entity.BlogTag;
import com.example.backend.entity.Tag;
import com.example.backend.service.BlogService;
import com.example.backend.service.BlogTagService;
import com.example.backend.service.CategoryService;
import com.example.backend.service.TagService;
import com.example.backend.utils.R;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * (Blog)表服务实现类
 *
 * @author makejava
 * @since 2023-10-18 20:59:05
 */
@Service("blogService")
@Slf4j
public class BlogServiceImpl extends ServiceImpl<BlogDao, Blog> implements BlogService {
    @Resource
    CategoryService categoryService;
    @Resource
    BlogTagService blogTagService;
    @Resource
    TagService tagService;
    public String getCategoryNameByCategoryId(Long categoryId){
        return categoryService.getById(categoryId).getCategoryName();
    }
    public List<Tag> getTagsByBlogId(Long blogId){
            QueryWrapper<BlogTag> blogTagQueryWrapper = new QueryWrapper<>();
            blogTagQueryWrapper.eq(DatabaseConstant.BlogTable.COLUMN_BLOG_ID,blogId);
        List<BlogTag> list = blogTagService.list(blogTagQueryWrapper);
        if (CollectionUtils.isEmpty(list)){
                return CollUtil.newArrayList();
            }
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.in(DatabaseConstant.CommonColumnEnum.ID.getName(),list);
            return tagService.list(tagQueryWrapper);
    }
    @Override
    public R<Page<BlogDto>> getBlogListByPageNum(Long page, Long pageNum, String name) {
        Page<Blog> blogPage = new Page<>(page,pageNum);
        Page<BlogDto> dtoPage = new Page<>();
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name),DatabaseConstant.BlogTable.COLUMN_TITLE,name);
        wrapper.orderByDesc(DatabaseConstant.CommonColumnEnum.CREATE_TIME.getName())
                .orderByDesc(DatabaseConstant.CommonColumnEnum.UPDATE_TIME.getName());
        this.page(blogPage,wrapper);
        List<Blog> blogList = blogPage.getRecords();
        List<BlogDto> dtoList =blogList.stream().map((item) -> {
            BlogDto blogDto = new BlogDto();
            BeanUtils.copyProperties(item,blogDto);
            if(!item.getCategoryId().isEmpty()) {
                String categoryName = getCategoryNameByCategoryId(Long.valueOf(item.getCategoryId()));
                blogDto.setCategoryName(categoryName);
            }
            List<Tag> list = getTagsByBlogId(Long.valueOf(item.getId()));
            blogDto.setTagList(list);
            return blogDto;
        }).toList();
        BeanUtils.copyProperties(blogPage,dtoPage, PageConstant.RECORD);
        dtoPage.setRecords(dtoList);
        return R.success("查询成功",dtoPage);
    }

    @Override
    public R<BlogDto> getBlogById(Long id) {
        Blog blog = getById(id);
        BlogDto data = new BlogDto();
        BeanUtils.copyProperties(blog,data);
        List<Tag> tagList = getTagsByBlogId(Long.valueOf(blog.getId()));
        data.setTagList(tagList);
        return R.success("查询成功",data);
    }

    @Override
    @Transactional
    public R saveOrUpdateBlog(String blog) {
        JSONObject object = JSONUtil.parseObj(blog);
        String string = object.get("data").toString();
        BlogDto data = JSONUtil.toBean(string, BlogDto.class);
        saveOrUpdate(data);
        List<Tag> tagList = data.getTagList();
        LambdaQueryWrapper<BlogTag> blogTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blogTagLambdaQueryWrapper.eq(BlogTag::getBlogId,data.getId());
        blogTagService.remove(blogTagLambdaQueryWrapper);
        List<BlogTag> list = tagList.stream().map(item -> BlogTag
                .builder()
                .blogId(data.getId())
                .tagId(item.getId()).build()
            ).toList();
        blogTagService.saveBatch(list);
        return R.success("操作成功");
    }

    @Override
    public R<Page<BlogDto>> getBlogByCategoryId(Long id,Long page,Long pageNum) {
        Page<Blog> blogPage = new Page<>(page,pageNum);
        Page<BlogDto> dtoPage = new Page<>();
        LambdaQueryWrapper<Blog> blogDtoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blogDtoLambdaQueryWrapper.eq(Blog::getCategoryId,id);
        blogDtoLambdaQueryWrapper.orderByDesc(Blog::getCreateTime).orderByDesc(Blog::getUpdateTime);
        this.page(blogPage,blogDtoLambdaQueryWrapper);
        List<Blog> blogList = blogPage.getRecords();
        List<BlogDto> dtoList =blogList.stream().map((item) -> {
            BlogDto blogDto = new BlogDto();
            BeanUtils.copyProperties(item,blogDto);
            String categoryName = getCategoryNameByCategoryId(Long.valueOf(item.getCategoryId()));
            blogDto.setCategoryName(categoryName);
            List<Tag> tagList = getTagsByBlogId(Long.valueOf(item.getId()));
            blogDto.setTagList(tagList);
            return blogDto;
        }).toList();
        BeanUtils.copyProperties(blogPage,dtoPage,PageConstant.RECORD);
        dtoPage.setRecords(dtoList);
        return R.success("查询成功",dtoPage);
    }

    @Override
    public R<Page<BlogDto>> getBlogByTagId(Long id, Long page, Long pageNum) {
        List<BlogTag> datalist = blogTagService.query().eq(DatabaseConstant.BlogTagTable.COLUMN_TAG_ID, id).list();
        List<String> blogIdList = datalist.stream().map(BlogTag::getBlogId).toList();
        if(blogIdList.isEmpty()){
            return R.failure(404,"该标签下没有文章");
        }
        Page<Blog> blogPage = new Page<>(page,pageNum);
        QueryWrapper<Blog> blogLambdaQueryWrapper = new QueryWrapper<>();
        blogLambdaQueryWrapper.in(DatabaseConstant.BlogTable.COLUMN_BLOG_ID,blogIdList).
                orderByDesc(DatabaseConstant.CommonColumnEnum.CREATE_TIME.getName())
                .orderByDesc(DatabaseConstant.CommonColumnEnum.UPDATE_TIME.getName());
        this.page(blogPage,blogLambdaQueryWrapper);
        Page<BlogDto> blogDtoPage = new Page<>();
        BeanUtils.copyProperties(blogPage,blogDtoPage,PageConstant.RECORD);
        List<Blog> records = blogPage.getRecords();
        List<BlogDto> dtoList = records.stream().map(item -> {
            BlogDto blogDto = new BlogDto();
            BeanUtils.copyProperties(item,blogDto);
            String categoryName = getCategoryNameByCategoryId(Long.valueOf(item.getCategoryId()));
            blogDto.setCategoryName(categoryName);
            List<Tag> tagList = getTagsByBlogId(Long.valueOf(item.getId()));
            blogDto.setTagList(tagList);
            return blogDto;
        }).toList();
        blogDtoPage.setRecords(dtoList);
        return R.success("查询成功",blogDtoPage);
    }

}

