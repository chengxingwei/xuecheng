package com.xuecheng.member.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    private String serviceName = "framework-member";


    private String serviceDesc = "授权中心";



    private String clientId = "client";

    private String clientSecret = "123456";

    private String OAuthServerUri = "http://localhost:9999/member";

    private static final Predicate<String> SWAGGER_PATHS = PathSelectors.regex("^(?!.*error$).*");
    public static final String securitySchemaOAuth2 = "oauth2schema";


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build().securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()));
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API")
                .description("test")
                .termsOfServiceUrl("")
                .contact(new Contact("wd", "", ""))
                .version("2.0")
                .build();
    }


    private OAuth securitySchema() {
        //这里设置 client 的 scope
        final AuthorizationScope authorizationScope = new AuthorizationScope("all", "允许测试阶段访问的所有接口");
        final GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(OAuthServerUri + "/oauth/token");
        return new OAuth(securitySchemaOAuth2, Arrays.asList(authorizationScope), Arrays.asList(grantType));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(this.defaultAuth())
                .forPaths(SWAGGER_PATHS)
                .build();
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration("client", "123456", "", "", "", ApiKeyVehicle.HEADER, "", " ");
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope =
                new AuthorizationScope("all", "允许测试阶段访问的所有接口");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference(securitySchemaOAuth2, authorizationScopes));
    }



}