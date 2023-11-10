package com.example.backend.entity;

/**
 * (WebStats)表实体类
 *
 * @author makejava
 * @since 2023-10-30 19:30:38
 */
@SuppressWarnings("serial")
public class WebStats {

    private Integer id;
//网站浏览人数
    private Integer views;
//博客总数
    private Integer blogCount;
//点赞总数
    private Integer likeCount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

}

