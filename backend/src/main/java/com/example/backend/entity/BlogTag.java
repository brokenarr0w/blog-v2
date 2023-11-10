package com.example.backend.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (BlogTag)表实体类
 *
 * @author makejava
 * @since 2023-10-24 13:37:48
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTag extends Model<BlogTag> {

    private String id;
//博客id
    private String blogId;
//标签id
    private String tagId;

}

