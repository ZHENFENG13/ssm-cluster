package com.ssm.cluster.dao;

import com.ssm.cluster.entity.Picture;

import java.util.List;
import java.util.Map;

/**
 * @author 13
 * @date 2018-02-11 13:39:08
 */
public interface PictureDao {
    /**
     * 返回相应的数据集合
     *
     * @param map
     * @return
     */
    List<Picture> findPictures(Map<String, Object> map);

    /**
     * 数据数目
     *
     * @param map
     * @return
     */
    int getTotalPictures(Map<String, Object> map);

    /**
     * 添加图片
     *
     * @param picture
     * @return
     */
    int insertPicture(Picture picture);

    /**
     * 修改图片
     *
     * @param picture
     * @return
     */
    int updPicture(Picture picture);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delPicture(Integer id);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Picture findPictureById(Integer id);

    /**
     * 批量删除
     *
     * @param id
     * @return
     */
    int deleteBatch(Object[] id);
}
