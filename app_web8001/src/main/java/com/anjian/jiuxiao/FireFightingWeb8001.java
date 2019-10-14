package com.anjian.jiuxiao;

import com.anjian.jiuxiao.service.FireFighting.FireIndexService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author ：努力吧小朱.
 * @version : Java1.8
 * @date ：Created in 09:16 2019/8/28
 * @ Description：${description}
 * @ Modified By：
 */
@SpringBootApplication
@ServletComponentScan
public class FireFightingWeb8001 {
    public static void main(String[] args) {
        SpringApplication.run(FireFightingWeb8001.class,args);

    }
}
