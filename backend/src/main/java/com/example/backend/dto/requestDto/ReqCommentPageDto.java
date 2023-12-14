package com.example.backend.dto.requestDto;

import lombok.Builder;
import lombok.Data;

/**
 * @author 湛蓝之翼
 * @version 1.0
 * @description 评论分页请求类
 * @date 2023/11/22 11:34
 */
@Data
@Builder
public class ReqCommentPageDto {
    /**
     * 当前页
     */
    Long page;
    /**
     * 页数大小
     */
    Long pageSize;
    /**
     * 评论种类（0是关于我，1是文章）
     */
    Integer type;
    /**
     * 文章ID（可空）
     */
    Long blogId;
}
