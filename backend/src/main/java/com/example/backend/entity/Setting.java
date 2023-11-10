package com.example.backend.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * (Setting)表实体类
 *
 * @author makejava
 * @since 2023-10-25 16:08:41
 */
@SuppressWarnings("serial")
@Data
public class Setting extends Model<Setting> {

    private Integer id;
//网站标题
    private String title;
//网站前缀
    private String suffix;
//版权
    private String copyright;
//页脚图片
    private String img;
//备案号
    private String record;

}

