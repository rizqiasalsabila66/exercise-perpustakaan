package com.winterhold.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

//agar url bisa memanggil method
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    //    set halaman landing, agar url bisa panjnag
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/category/index");
        registry.addViewController("/category").setViewName("forward:/category/index");
        registry.addViewController("/author").setViewName("forward:/author/index");
        registry.addViewController("/customer").setViewName("forward:/customer/index");
        }

}

