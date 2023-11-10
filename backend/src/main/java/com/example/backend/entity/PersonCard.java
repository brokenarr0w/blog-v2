package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * (PersonCard)表实体类
 *
 * @author makejava
 * @since 2023-10-25 16:10:54
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonCard {

    private Integer id;
//头像
    private String avatar;
//昵称
    private String name;
//滚动个签
    private String rollText;
//github
    private String github;
//网易云
    private String music;
//qq
    private String qq;
//bilibili
    private String bilibili;
//自定义1
    private String diy1;
//自定义2
    private String diy2;


}

