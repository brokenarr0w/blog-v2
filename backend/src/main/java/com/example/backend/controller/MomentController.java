package com.example.backend.controller;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Moment;
import com.example.backend.entity.WebStats;
import com.example.backend.service.MomentService;
import com.example.backend.service.WebStatsService;
import com.example.backend.utils.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * (Moment)表控制层
 *
 * @author makejava
 * @since 2023-10-25 15:18:46
 */
@RestController
@RequestMapping("moment")
@CrossOrigin
@Slf4j
public class MomentController{
    /**
     * 服务对象
     */
    @Resource
    private MomentService momentService;
    @Resource
    private WebStatsService webStatsService;
    @GetMapping("/page")
    public R<Page<Moment>> momentPage(Long page,Long pageNum,String name){
        return momentService.getMomentListByPageNum(page, pageNum, name);
    }
    @GetMapping
    public R<Moment> get(Long id){
        return R.success("查询成功",momentService.getById(id));
    }
    @PostMapping
    @SaCheckLogin
    public R<Object> save(@RequestBody JSONObject moment){
        Object data = moment.get("data");
        Moment bean = JSONUtil.toBean(JSONUtil.parseObj(data), Moment.class);
        momentService.saveOrUpdate(bean);
        return R.success("这个世界多了一些碎碎念");
    }
    @GetMapping("/count")
    public R<Long> count(){
        return R.success("查询成功",momentService.count());
    }
    @DeleteMapping
    public R<Object> delete(Long id){
        momentService.removeById(id);
        return R.success("你撤回了一条动态");
    }
    @PostMapping("like")
    public R<Object> like(@RequestBody JSONObject object){
        String id =(String) object.get("id");
        Moment moment = momentService.getById(id);
        moment.setLikes(moment.getLikes()+1);
        WebStats one = webStatsService.getOne(null);
        one.setLikeCount(one.getLikeCount()+1);
        webStatsService.saveOrUpdate(one);
        momentService.update(moment,null);
        return R.success("点赞成功");
    }
}

