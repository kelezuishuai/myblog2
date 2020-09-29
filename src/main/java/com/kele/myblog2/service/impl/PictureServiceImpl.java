package com.kele.myblog2.service.impl;

import com.kele.myblog2.entity.Picture;
import com.kele.myblog2.mapper.PicturesMapper;
import com.kele.myblog2.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/26
 * @Content:
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PicturesMapper picturesMapper;

    @Override
    public List<Picture> listPicture() {
        return picturesMapper.listPicture();
    }

    @Override
    public int savePicture(Picture picture) {
        return picturesMapper.savePicture(picture);
    }

    @Override
    public Picture getPicture(Long id) {
        return picturesMapper.getPicture(id);
    }

    @Override
    public int updatePicture(Picture picture) {
        return picturesMapper.updatePicture(picture);
    }

    @Override
    public void deletePicture(Long id) {

        picturesMapper.deletePicture(id);
    }
}
