package com.ssm.cluster.controller;

import java.util.List;
import java.util.Map;

import com.ssm.cluster.entity.Article;
import com.ssm.cluster.service.ArticleService;
import com.ssm.cluster.utils.R;
import com.ssm.cluster.utils.PageUtils;
import com.ssm.cluster.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13
 * @date 2018-02-11 13:39:08
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Article> articles = articleService.queryList(query);
		int total = articleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(articles, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		Article article = articleService.queryObject(id);
		
		return R.ok().put("article", article);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody Article article){
		articleService.save(article);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody Article article){
		articleService.update(article);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		articleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
