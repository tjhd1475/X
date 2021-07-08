package com.qiashe.service.impl;

import com.qiashe.dao.ImagesDao;
import com.qiashe.domain.Image;
import com.qiashe.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesServiceImpl implements ImagesService {
    @Autowired
    private ImagesDao dao;
    @Override
    public int uploadImage(Image image) {
        int num=0;
        if(image.getIsMainImg()==1){
            Image oldImg=dao.selectMainImage(image.getCanteenItemId());
            if(oldImg!=null){
                dao.updateToNotMainImage(oldImg.getImageId());
            }
        }
        dao.insertImage(image);
        return num;
    }

    @Override
    public List<Image> findImgByCIName(String name) {
        List<Image> images= dao.selectByCIName(name);
        return images;
    }

    @Override
    public List<Image> findImgByCIId(Integer CIId) {
        List<Image> images= dao.selectByCIId(CIId);
        return images;
    }

    @Override
    public Image findImgById(Integer imageId) {
        Image image=dao.selectImageById(imageId);
        return image;
    }

    @Override
    public List<Image> FindAll() {
        List<Image> images=dao.selectAll();
        return images;
    }

    @Override
    public int RemoveItem(Integer id) {
        int num= dao.DeleteItem(id);
        return num;
    }
}
