package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.dao.MomentDao;
import com.example.backend.entity.Moment;
import com.example.backend.service.MomentService;
import com.example.backend.utils.R;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * (Moment)表服务实现类
 *
 * @author makejava
 * @since 2023-10-25 15:18:46
 */
@Service("momentService")
public class MomentServiceImpl extends ServiceImpl<MomentDao, Moment> implements MomentService {

    @Override
    public R<Page<Moment>> getMomentListByPageNum(Long page, Long pageNum, String name) {
        Page<Moment> momentPage = new Page<>(page,pageNum);
        LambdaQueryWrapper<Moment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(name),Moment::getContent,name);
        lambdaQueryWrapper.orderByDesc(Moment::getCreateTime);
        this.page(momentPage,lambdaQueryWrapper);
        return R.success("查询成功",momentPage);
    }
}

