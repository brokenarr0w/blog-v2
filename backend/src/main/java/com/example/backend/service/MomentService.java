package com.example.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.Moment;
import com.example.backend.utils.R;

/**
 * (Moment)表服务接口
 *
 * @author makejava
 * @since 2023-10-25 15:18:46
 */
public interface MomentService extends IService<Moment> {
    R<Page<Moment>> getMomentListByPageNum(Long page,Long pageNum,String name);
}

