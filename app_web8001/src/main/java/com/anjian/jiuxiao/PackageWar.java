package com.anjian.jiuxiao;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author ：努力吧小朱.
 * @version : Java1.8
 * @date ：Created in 13:51 2019/8/28
 * @ Description：${description}
 * @ Modified By：
 */
public class PackageWar extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FireFightingWeb8001.class);
    }
}
