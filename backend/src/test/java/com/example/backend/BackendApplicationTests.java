package com.example.backend;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() throws IOException {
        System.out.println(SecureUtil.md5("dou9512357@@"));
    }

}
