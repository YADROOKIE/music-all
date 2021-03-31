package com.yad.web.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfigure implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String basePath = new File("file").getAbsolutePath();
        System.out.println(basePath+"basePath");
        String fileBasePath = "file:" + basePath + "/";

        registry.addResourceHandler("/**").
                //保证其他资源不会被屏蔽
                        addResourceLocations("classpath:/META-INF/resources/").
                //设置入我们的基础路径
                        addResourceLocations(fileBasePath);
    }
}
