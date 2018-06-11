package com.ssm.cluster.controller;

import com.ssm.cluster.entity.User;
import com.ssm.cluster.service.UserService;
import com.ssm.cluster.utils.MD5Util;
import com.ssm.cluster.utils.PageUtils;
import com.ssm.cluster.utils.Result;
import com.ssm.cluster.utils.SearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Result login(@RequestBody User user) {
        try {
            String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
            user.setPassword(MD5pwd);
        } catch (Exception e) {
            user.setPassword("");
        }
        User resultUser = userService.login(user);
        if (resultUser == null) {
            return Result.error("请认真核对账号、密码！");
        } else {
            resultUser.setPassword("PASSWORD");
            return Result.ok().put("currentUser", resultUser);
        }
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        SearchQuery query = new SearchQuery(params);

        List<User> users = userService.queryList(query);
        int total = userService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(users, total, query.getLimit(), query.getPage());

        return Result.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id) {
        User user = userService.queryObject(id);
        return Result.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return Result.error("缺少参数！");
        }
        if ("admin".endsWith(user.getUserName().trim())) {
            return Result.error("不能添加admin用户！");
        }
        if (userService.queryTotal(null) > 100) {
            return Result.error("测试用户数已超过100，请联系管理者清理！");
        }
        userService.save(user);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/updatePassword")
    public Result update(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getPassword())) {
            return Result.error("请输入密码！");
        }
        User tempUser = userService.queryObject(user.getId());
        if (tempUser == null) {
            return Result.error("无此用户！");
        }
        if ("admin".endsWith(user.getUserName().trim())) {
            return Result.error("不能修改admin用户！");
        }
        tempUser.setPassword(user.getPassword());
        userService.updatePassword(user);
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] ids) {
        userService.deleteBatch(ids);

        return Result.ok();
    }

}
