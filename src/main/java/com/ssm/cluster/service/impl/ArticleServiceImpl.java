package com.ssm.cluster.service.impl;

import com.ssm.cluster.utils.AntiXssUtil;
import com.ssm.cluster.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ssm.cluster.dao.ArticleDao;
import com.ssm.cluster.entity.Article;
import com.ssm.cluster.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article queryObject(Integer id) {
        return articleDao.getArticleById(id);
    }

    @Override
    public List<Article> queryList(Map<String, Object> map) {
        List<Article> articles = articleDao.findArticles(map);
        return articles;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return articleDao.getTotalArticles(map);
    }

    @Override
    public void save(Article article) {
        try {
            article.setArticleCreateDate(DateUtil.getCurrentDateStr());
            article.setArticleTitle(AntiXssUtil.replaceHtmlCode(article.getArticleTitle()));
            articleDao.insertArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Article article) {
        article.setArticleTitle(AntiXssUtil.replaceHtmlCode(article.getArticleTitle()));
        articleDao.updArticle(article);
    }

    @Override
    public void delete(Integer id) {
        articleDao.delArticle(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        articleDao.deleteBatch(ids);
    }

}
