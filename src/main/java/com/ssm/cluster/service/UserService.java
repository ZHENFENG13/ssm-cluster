package com.ssm.cluster.service;

import com.ssm.cluster.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author 13
 * @date 2018-02-11 13:39:08
 */
public interface UserService {

	User login(User user);

	List<User> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(User user);
	
	void updatePassword(User user);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	User queryObject(Integer id);
}
