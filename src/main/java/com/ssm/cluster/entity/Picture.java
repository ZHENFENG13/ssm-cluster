package com.ssm.cluster.entity;

import java.io.Serializable;

/**
 * @author 13
 * @date 2018-02-11 13:39:08
 */
public class Picture implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //图片地址
    private String path;
    //图片类型
    private Integer type;
    //添加时间
    private String time;
    //点击图片的跳转链接
    private String url;
    //图片类型
    private Integer grade;

    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：图片地址
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取：图片地址
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置：图片类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：图片类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：添加时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取：添加时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置：点击图片的跳转链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：点击图片的跳转链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：图片类型
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取：图片类型
     */
    public Integer getGrade() {
        return grade;
    }
}
