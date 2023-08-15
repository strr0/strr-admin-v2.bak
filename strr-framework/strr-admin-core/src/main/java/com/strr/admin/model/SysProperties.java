package com.strr.admin.model;

public class SysProperties {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 应用
     */
    private String application;

    /**
     * 环境
     */
    private String profile;

    /**
     * 标签
     */
    private String label;

    /**
     * 键
     */
    private String key;

    /**
     * 键名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
