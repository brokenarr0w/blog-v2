package com.example.backend.constants;

import lombok.Getter;

/**
 * @author 湛蓝之翼
 * @version 1.0
 * @description 数据库常量类
 * @date 2023/11/15 11:18
 */
public class DatabaseConstant {
    public static class BlogTable{
        public static final String COLUMN_BLOG_ID = "id";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_TITLE = "title";
    }
    public static class BlogTagTable{
        public static final String COLUMN_BLOG_ID = "blog_id";
        public static final String COLUMN_TAG_ID = "tag_id";
    }
    public static class MomentTable{
        public static final String COLUMN_CONTENT = "content";
    }
    @Getter
    public enum CommonColumnEnum{
        CREATE_TIME("create_time"),
        UPDATE_TIME("update_time"),
        ID("id");
        private final String name;

        CommonColumnEnum(String name) {
            this.name = name;
        }

    }
}
