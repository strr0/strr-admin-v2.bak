package com.strr.generator.config;

import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.ModelType;

public class CustomContext extends Context {
    private String targetPackage;
    private String targetProject;
    private String clientProject;

    public CustomContext(ModelType defaultModelType) {
        super(defaultModelType);
        super.setId("simple");
        super.setTargetRuntime("com.strr.generator.config.CustomIntrospectedTable");
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getClientProject() {
        return clientProject;
    }

    public void setClientProject(String clientProject) {
        this.clientProject = clientProject;
    }
}
