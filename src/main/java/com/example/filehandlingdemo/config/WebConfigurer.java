package com.example.filehandlingdemo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.io.File;
import java.util.Objects;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.filehandlingdemo.config")
public class WebConfigurer implements WebMvcConfigurer {
    public static String uploadDirectory =
            System.getProperty("user.home") + "/uploads/driver";
//  		 "/root/uploads";

    public static String currentDirectory = Objects.requireNonNull(
            WebConfigurer.class.getResource("")).getPath();

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        currentDirectory = currentDirectory.replace("com/example/filehandlingdemo/config", "static");
        File file = new File(uploadDirectory);
        if (!file.exists())
            file.mkdir();

        registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + uploadDirectory + "/");
        //+"/WEB-INF/classes/static"+"/");
        registry.addResourceHandler("/**").addResourceLocations("file:" + currentDirectory + "/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/h2-console");

        registry.addResourceHandler(
                        "/webjars/**", "/img/**", "/css/**", "/scss/**", "/plugins/**", "/js/**", "/fonts/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/scss/",
                        "classpath:/static/plugins/",
                        "classpath:/static/js/",
                        "classpath:/static/fonts/");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }


    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

        // Read i18n/messages_xxx.properties file.
        // For example: i18n/messages_en.properties
        messageResource.setBasename("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");


        registry.addInterceptor(localeInterceptor).addPathPatterns("/*");
    }
}

