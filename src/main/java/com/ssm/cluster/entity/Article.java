package com.ssm.cluster.entity;

import java.io.Serializable;

/**
 * @author 13
 * @date 2018-02-11 13:39:08
 */
public class Article implements Serializable {
    //主键
    private Integer id;
    //文章标题
    private String articleTitle;
    //创建时间
    private String articleCreateDate;
    //文章内容
    private String articleContent;
    //是否置顶，1为置顶，默认为0
    private Integer isTop;
    //添加人
    private String addName;

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
     * 设置：文章标题
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * 获取：文章标题
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * 设置：创建时间
     */
    public void setArticleCreateDate(String articleCreateDate) {
        this.articleCreateDate = articleCreateDate;
    }

    /**
     * 获取：创建时间
     */
    public String getArticleCreateDate() {
        return articleCreateDate;
    }

    /**
     * 设置：文章内容
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    /**
     * 获取：文章内容
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * 设置：是否置顶，1为置顶，默认为0
     */
    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    /**
     * 获取：是否置顶，1为置顶，默认为0
     */
    public Integer getIsTop() {
        return isTop;
    }

    /**
     * 设置：添加人
     */
    public void setAddName(String addName) {
        this.addName = addName;
    }

    /**
     * 获取：添加人
     */
    public String getAddName() {
        return addName;
    }
}
