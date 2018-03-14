package com.ssm.cluster.service.impl;

import com.ssm.cluster.utils.AntiXssUtil;
import com.ssm.cluster.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ssm.cluster.dao.PictureDao;
import com.ssm.cluster.entity.Picture;
import com.ssm.cluster.service.PictureService;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDao pictureDao;

    @Override
    public Picture queryObject(Integer id) {
        return pictureDao.findPictureById(id);
    }

    @Override
    public List<Picture> queryList(Map<String, Object> map) {
        return pictureDao.findPictures(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return pictureDao.getTotalPictures(map);
    }

    @Override
    public void save(Picture picture) {
        try {
            picture.setTime(DateUtil.getCurrentDateStr());
            picture.setUrl(AntiXssUtil.replaceHtmlCode(picture.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        pictureDao.insertPicture(picture);
    }

    @Override
    public void update(Picture picture) {
        picture.setUrl(AntiXssUtil.replaceHtmlCode(picture.getUrl()));
        pictureDao.updPicture(picture);
    }

    @Override
    public void delete(Integer id) {
        pictureDao.delPicture(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        pictureDao.deleteBatch(ids);
    }

}
