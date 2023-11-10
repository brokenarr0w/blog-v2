package com.example.backend.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Tag)表实体类
 *
 * @author makejava
 * @since 2023-10-18 21:07:19
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends Model<Tag> {

    private String id;
//标签名
    private String name;
    private String color;


}

