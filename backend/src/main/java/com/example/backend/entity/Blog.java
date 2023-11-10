package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * (Blog)表实体类
 *
 * @author makejava
 * @since 2023-10-18 20:59:05
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog extends Model<Blog> {
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;
//文章首图（这里只存名字）
    private String image;
    private String title;
//文章摘要
    private String description;
//文章内容
    private String content;



    //文章创建时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
    private boolean isTop;

    //文章更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;
//文章所属分类id
    private String categoryId;
//文章字数
    private Integer words;
//文章观看数
    private Integer views;
    private Integer time;

    //文章点赞数
    private Integer likes;


}

