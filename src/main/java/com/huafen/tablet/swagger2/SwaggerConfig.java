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
    public Docket hisDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("呼叫历史")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.his"))//扫描的包路径
                .build();
    }
    
    @Bean
    public Docket msgSaveDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("呼叫保存")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.save"))//扫描的包路径
                .build();
    }
    
    
    @Bean
    public Docket sendMsgDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("消息推送")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.send"))//扫描的包路径
                .build();
    }
    
    @Bean
    public Docket iotDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("物联状态")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.iot"))//扫描的包路径
                .build();
    }
    
    
    @Bean
    public Docket authDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) 
                .groupName("呼叫权限")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.auth"))//扫描的包路径
                .build();
    }
    
    @Bean
    public Docket OrgServiceDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) 
                .groupName("组织中台信息")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huafen.tablet.controller.user"))//扫描的包路径
                .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("平板借还服务接口文档")
                .version("1.0.0")
                .build();
    }
}

