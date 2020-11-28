package com.zgy.handle.gateway.config.swagger;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: a4423
 * @date: 2020/9/24 22:39
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList();
        resources.add(swaggerResource("用户接口","/userservice/v2/api-docs","1.0"));
        resources.add(swaggerResource("知识库接口","/knowledgeservice/v2/api-docs","1.0"));
        resources.add(swaggerResource("一卡通接口","/cardservice/v2/api-docs","1.0"));
        resources.add(swaggerResource("股票接口","/stockservice/v2/api-docs","1.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
