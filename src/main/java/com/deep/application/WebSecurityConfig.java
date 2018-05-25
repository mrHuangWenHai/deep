package com.deep.application;

import com.deep.api.authorization.interceptor.AuthorizationInterceptor;
import com.deep.api.authorization.interceptor.PermitInterceptor;
import com.deep.api.authorization.interceptor.testInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.filter.HttpPutFormContentFilter;
import java.io.File;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new testInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new PermitInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {

      File directory = new File("");//参数为空
      try {

        String directoryPath = directory.getCanonicalPath() ;

        String picturePath = directoryPath + "/picture/";
        File pictureFile = new File(picturePath);
        if (!pictureFile.exists()) {
          pictureFile.mkdirs();
        }

        System.out.println(picturePath);

        String videoPath = directoryPath + "/video/";
        File videoFile  = new File(videoPath);
        if (!videoFile.exists()) {
          videoFile.mkdirs();
        }

        String picPath = directoryPath + "/pic/";
        File picFile  = new File(picPath);
        if (!picFile.exists()) {
          picFile.mkdirs();
        }

        registry.addResourceHandler("/picture/**").addResourceLocations("file://"+picturePath);
        registry.addResourceHandler("/movie/**").addResourceLocations("file://"+videoPath);
        registry.addResourceHandler("/pic/**").addResourceLocations("file://"+picPath);

        System.out.println("this is the ResourceHandler");
      } catch (Exception e) {
        System.out.println(e);
      }
    }
}
