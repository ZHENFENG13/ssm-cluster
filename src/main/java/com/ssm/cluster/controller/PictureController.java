package com.ssm.cluster.controller;

import com.ssm.cluster.entity.Picture;
import com.ssm.cluster.service.PictureService;
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
@RequestMapping("/pictures")
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public Result list(@RequestParam Map<String, Object> params){
		//查询列表数据
        SearchQuery query = new SearchQuery(params);

		List<Picture> pictures = pictureService.queryList(query);
		int total = pictureService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(pictures, total, query.getLimit(), query.getPage());
		
		return Result.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public Result info(@PathVariable("id") Integer id){
		Picture picture = pictureService.queryObject(id);
		
		return Result.ok().put("picture", picture);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public Result save(@RequestBody Picture picture){
		pictureService.save(picture);
		
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Picture picture){
		pictureService.update(picture);
		
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public Result delete(@RequestBody Integer[] ids){
		pictureService.deleteBatch(ids);
		
		return Result.ok();
	}
	
}
