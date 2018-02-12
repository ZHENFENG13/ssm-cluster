package com.ssm.cluster.service;

import com.ssm.cluster.entity.Picture;

import java.util.List;
import java.util.Map;

/**
 * @author 13
 * @date 2018-02-11 13:39:08
 */
public interface PictureService {
	
	Picture queryObject(Integer id);
	
	List<Picture> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(Picture picture);
	
	void update(Picture picture);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
