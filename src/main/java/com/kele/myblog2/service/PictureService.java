package com.kele.myblog2.service;

import com.kele.myblog2.entity.Picture;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/26
 * @Content:
 */
public interface PictureService {

    //查询所有图片
    List<Picture> listPicture();

    //保存图片
    int savePicture(Picture picture);

    //通过id拿到图片
    Picture getPicture(Long id);

    //修改图片链接
    int updatePicture(Picture picture);

    //删除图片
    void deletePicture(Long id);
}
