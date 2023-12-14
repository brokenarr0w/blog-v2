package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2023-11-22 11:37:16
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = -44222395763958671L;

    private Long id;
/**
     * 评论人的名字
     */
    private String name;
/**
     * 评论人的邮箱
     */
    private String email;
/**
     * 评论人的操作系统
     */
    private String platform;
/**
     * 评论人的浏览器名
     */
    @TableField(value = "appName")
    private String appName;
/**
     * 评论内容
     */
    private String content;
/**
     * 评论的类型(1是文章评论,2是关于我的评论)
     */
    private Integer commentType;
/**
     * （可选）来自于哪个文章
     */
    private Long blogId;
/**
     * 评论创建时间
     */
@TableField(fill = FieldFill.INSERT)
@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

}

