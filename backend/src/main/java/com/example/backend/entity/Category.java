package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * (Category)表实体类
 *
 * @author makejava
 * @since 2023-10-18 21:04:13
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
public class Category extends Model<Category> {
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;
//分类名称
    private String categoryName;

}

