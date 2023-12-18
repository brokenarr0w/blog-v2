package com.example.backend.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 湛蓝之翼
 * @version 1.0
 * @description 文章标题响应类
 * @date 2023/12/18 18:03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogTitleDto {
    /**
     * 文章id
     */
    String id;
    /**
     * 文章标题
     */
    String title;
}
