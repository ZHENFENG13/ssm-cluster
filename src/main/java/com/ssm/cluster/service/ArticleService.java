package com.ssm.cluster.service;

import com.ssm.cluster.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * @author 13
 * @date 2018-02-11 13:39:08
 */
public interface ArticleService {
	
	Article queryObject(Integer id);
	
	List<Article> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(Article article);
	
	void update(Article article);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
