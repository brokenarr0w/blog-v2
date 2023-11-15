package com.example.backend.dto;

import com.example.backend.entity.Blog;
import com.example.backend.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 湛蓝之翼
 * @version 1.0
 * @description: TODO
 * @date 2023/10/24 12:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogDto extends Blog {
    String categoryName;
    List<Tag> tagList;


}
