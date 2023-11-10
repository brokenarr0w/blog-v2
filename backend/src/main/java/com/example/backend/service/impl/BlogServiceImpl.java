package com.example.backend.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.dao.BlogDao;
import com.example.backend.dao.TagDao;
import com.example.backend.dto.BlogDto;
import com.example.backend.entity.Blog;
import com.example.backend.entity.BlogTag;
import com.example.backend.entity.Category;
import com.example.backend.entity.Tag;
import com.example.backend.service.BlogService;
import com.example.backend.service.BlogTagService;
import com.example.backend.service.CategoryService;
import com.example.backend.utils.R;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    TagDao tagDao;
    @Override
    public R<Page<BlogDto>> getBlogListByPageNum(Long page, Long pageNum, String name) {
        Map<String,String> category = new HashMap<>();
        List<Category> categories = categoryService.list();
        categories.forEach((item) -> {
            category.put(item.getId(), item.getCategoryName());
        });
        log.info(category.toString());
        Page<Blog> blogPage = new Page<>(page,pageNum);
        Page<BlogDto> dtoPage = new Page<>();
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name),Blog::getTitle,name);
        wrapper.orderByDesc(Blog::getCreateTime).orderByDesc(Blog::getUpdateTime);
        this.page(blogPage,wrapper);
        List<Blog> blogList = blogPage.getRecords();
        List<BlogDto> dtoList =blogList.stream().map((item) -> {
            BlogDto blogDto = new BlogDto();
            BeanUtils.copyProperties(item,blogDto);
            String categoryName = category.get(item.getCategoryId());
            log.info(categoryName);
            blogDto.setCategoryName(categoryName);
            List<String> idList = new ArrayList<>();
            LambdaQueryWrapper<BlogTag> blogTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            blogTagLambdaQueryWrapper.eq(BlogTag::getBlogId,item.getId());
            List<BlogTag> list = blogTagService.list(blogTagLambdaQueryWrapper);
            if(list.isEmpty()){
               return blogDto;
            }
            list.forEach((data) -> idList.add(data.getTagId()));
            LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tagLambdaQueryWrapper.in(Tag::getId,idList);
            List<Tag> tagList = tagDao.selectList(tagLambdaQueryWrapper);
            blogDto.setTagList(tagList);
            return blogDto;
        }).toList();
        BeanUtils.copyProperties(blogPage,dtoPage,"records");
        dtoPage.setRecords(dtoList);
        return R.success("查询成功",dtoPage);
    }

    @Override
    public R<BlogDto> getBlogById(Long id) {
        Blog blog = getById(id);
        BlogDto data = new BlogDto();
        BeanUtils.copyProperties(blog,data);
        List<Long> idList = new ArrayList<>();
        LambdaQueryWrapper<BlogTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogTag::getBlogId,blog.getId());
        List<BlogTag> list = blogTagService.list(wrapper);
        if(!list.isEmpty()) {
            list.forEach((item) -> idList.add(Long.valueOf(item.getTagId())));
            LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tagLambdaQueryWrapper.in(Tag::getId, idList);
            List<Tag> tagList = tagDao.selectList(tagLambdaQueryWrapper);
            data.setTagList(tagList);
        }
        return R.success("查询成功",data);
    }

    @Override
    @Transactional
    public R saveOrUpdateBlog(String blog) {
        Map<String,String> category = new HashMap<>();
        List<Category> categories = categoryService.list();
        categories.forEach((item) -> {
            category.put(item.getId(), item.getCategoryName());
        });
        log.info(blog);
        JSONObject object = JSONUtil.parseObj(blog);

        String string = object.get("data").toString();
        BlogDto data = JSONUtil.toBean(string, BlogDto.class);
        saveOrUpdate(data);
        List<Tag> tagList = data.getTagList();
        List<BlogTag> list = new ArrayList<>();
        LambdaQueryWrapper<BlogTag> blogTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blogTagLambdaQueryWrapper.eq(BlogTag::getBlogId,data.getId());
        blogTagService.remove(blogTagLambdaQueryWrapper);

        tagList.forEach((item) -> {
            BlogTag blogTag = new BlogTag();
            blogTag.setBlogId(data.getId());
            blogTag.setTagId(item.getId());
            list.add(blogTag);
        });
        blogTagService.saveBatch(list);
        return null;
    }

    @Override
    public R<Page<BlogDto>> getBlogByCategoryId(Long id,Long page,Long pageNum) {
        Map<Long,String> category = new HashMap<>();
        List<Category> categories = categoryService.list();
        categories.forEach((item) -> {
            category.put(Long.valueOf(item.getId()), item.getCategoryName());
        });
        log.info(category.toString());
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
            String categoryName = category.get(Long.valueOf(item.getCategoryId()));
            blogDto.setCategoryName(categoryName);
            List<String> idList = new ArrayList<>();
            LambdaQueryWrapper<BlogTag> blogTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            blogTagLambdaQueryWrapper.eq(BlogTag::getBlogId,item.getId());
            List<BlogTag> list = blogTagService.list(blogTagLambdaQueryWrapper);
            if(list.isEmpty()){
                return blogDto;
            }
            list.forEach((data) -> idList.add(data.getTagId()));
            LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tagLambdaQueryWrapper.in(Tag::getId,idList);
            List<Tag> tagList = tagDao.selectList(tagLambdaQueryWrapper);
            blogDto.setTagList(tagList);
            return blogDto;
        }).toList();
        BeanUtils.copyProperties(blogPage,dtoPage,"records");
        dtoPage.setRecords(dtoList);
        return R.success("查询成功",dtoPage);
    }

    @Override
    public R<Page<BlogDto>> getBlogByTagId(Long id, Long page, Long pageNum) {

        List<BlogTag> datalist = blogTagService.query().eq("tag_id", id).list();
        List<String> blogIdList = datalist.stream().map(BlogTag::getBlogId).toList();
        if(blogIdList.isEmpty()){
            return R.failure(404,"该标签下没有文章");
        }
        Map<Long,String> category = new HashMap<>();
        List<Category> categories = categoryService.list();
        categories.forEach((item) -> {
            category.put(Long.valueOf(item.getId()), item.getCategoryName());
        });
        Page<Blog> blogPage = new Page<>(page,pageNum);
        LambdaQueryWrapper<Blog> blogLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blogLambdaQueryWrapper.in(Blog::getId,blogIdList).
                orderByDesc(Blog::getCreateTime).orderByDesc(Blog::getUpdateTime);
        this.page(blogPage,blogLambdaQueryWrapper);
        Page<BlogDto> blogDtoPage = new Page<>();
        BeanUtils.copyProperties(blogPage,blogDtoPage,"records");
        List<Blog> records = blogPage.getRecords();
        List<BlogDto> dtoList = records.stream().map(item -> {
            BlogDto blogDto = new BlogDto();
            BeanUtils.copyProperties(item,blogDto);
            String categoryName = category.get(Long.valueOf(item.getCategoryId()));
            blogDto.setCategoryName(categoryName);
            List<String> idList = new ArrayList<>();
            LambdaQueryWrapper<BlogTag> blogTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            blogTagLambdaQueryWrapper.eq(BlogTag::getBlogId,item.getId());
            List<BlogTag> list = blogTagService.list(blogTagLambdaQueryWrapper);
            if(list.isEmpty()){
                return blogDto;
            }
            list.forEach((data) -> idList.add(data.getTagId()));
            LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tagLambdaQueryWrapper.in(Tag::getId,idList);
            List<Tag> tagList = tagDao.selectList(tagLambdaQueryWrapper);
            blogDto.setTagList(tagList);
            return blogDto;
        }).toList();
        blogDtoPage.setRecords(dtoList);
        return R.success("查询成功",blogDtoPage);
    }

}

