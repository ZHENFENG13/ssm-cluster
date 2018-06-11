package com.ssm.cluster.controller;

import com.ssm.cluster.entity.Article;
import com.ssm.cluster.service.ArticleService;
import com.ssm.cluster.utils.PageUtils;
import com.ssm.cluster.utils.Result;
import com.ssm.cluster.utils.SearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
	public Result list(@RequestParam Map<String, Object> params){
		//查询列表数据
        SearchQuery query = new SearchQuery(params);

		List<Article> articles = articleService.queryList(query);
		int total = articleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(articles, total, query.getLimit(), query.getPage());
		
		return Result.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public Result info(@PathVariable("id") Integer id){
		Article article = articleService.queryObject(id);
		
		return Result.ok().put("article", article);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public Result save(@RequestBody Article article){
		articleService.save(article);
		
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Article article){
		articleService.update(article);
		
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public Result delete(@RequestBody Integer[] ids){
		articleService.deleteBatch(ids);
		
		return Result.ok();
	}
	
}
