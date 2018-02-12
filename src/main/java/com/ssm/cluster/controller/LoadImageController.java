package com.ssm.cluster.controller;

import com.ssm.cluster.common.Constants;
import com.ssm.cluster.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by 13 on 2017/4/7.
 */
@Controller
@RequestMapping("/images")
public class LoadImageController {
    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public R upload(@RequestParam("file") MultipartFile file) {
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String imgName;
        if (type.equals("jpg")) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".jpg";
        } else if (type.equals("png")) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".png";
        } else if (type.equals("jpeg")) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".jpeg";
        } else {
            return null;
        }
        File newFile = new File(Constants.UPLOAD_PATH + imgName);
        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("url", "/upload/" + imgName);
    }
}
