package com.huafen.tablet.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig{
    @Bean
    public Docket applyDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("平板申请相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.apply"))//扫描的包路径
                .build();
    }
    
    @Bean
    public Docket bindDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("平板借用相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.bind"))//扫描的包路径
                .build();
    }
    
    @Bean
    public Docket hisDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("平板借还历史接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.his"))//扫描的包路径
                .build();
    }
    
    @Bean
    public Docket revertDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("平板归还接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.revert"))//扫描的包路径
                .build();
    }
    
    @Bean
    public Docket orgServiceDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) 
                .groupName("组织中台信息")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.user"))//扫描的包路径
                .build();
    }
    
    @Bean
    public Docket pushDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("平板信息维护相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.tablet"))//扫描的包路径
                .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("平板借还服务接口文档")
                .version("1.0.0")
                .build();
    }
}

