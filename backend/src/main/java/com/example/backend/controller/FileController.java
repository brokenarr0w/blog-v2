package com.example.backend.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.example.backend.utils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 湛蓝之翼
 * @version 1.0
 * @description:
 * @date 2023/10/29 17:16
 */
@RestController
@CrossOrigin
public class FileController {
    @PostMapping("/upload")
    @SaCheckLogin
    public R<String> upload(@RequestParam(value = "file")MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        String path = "/www/wwwroot/blog-v2/static/";
        //用于查看路径是否正确
        System.out.println(path);
        File data = new File(path);
        if(!data.exists()){
            data.mkdirs();
        }
        File file1 = new File(data, fileName);
        if(file1.exists()){
            return R.success("上传成功",fileName);
        }
        file.transferTo(file1);
        return R.success("上传成功",fileName);
    }
}
