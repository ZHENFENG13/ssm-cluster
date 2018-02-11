package com.ssm.cluster.controller;

import java.util.List;
import java.util.Map;

import com.ssm.cluster.entity.User;
import com.ssm.cluster.service.UserService;
import com.ssm.cluster.utils.MD5Util;
import com.ssm.cluster.utils.PageUtils;
import com.ssm.cluster.utils.Query;
import com.ssm.cluster.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 13
 * @date 2018-02-11 13:39:08
 */
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 登录
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/cookie", method = RequestMethod.POST)
	public R login(User user) {
		try {
			String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
			user.setPassword(MD5pwd);
		} catch (Exception e) {
			user.setPassword("");
		}
		User resultUser = userService.login(user);
		if (resultUser == null) {
			return R.error("请认真核对账号、密码！");
		} else {
			resultUser.setPassword("PASSWORD");
			return R.ok().put("currentUser", resultUser);
		}
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<User> users = userService.queryList(query);
		int total = userService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(users, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		User user = userService.queryObject(id);
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody User user){
		userService.save(user);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody User user){
		userService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		userService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
