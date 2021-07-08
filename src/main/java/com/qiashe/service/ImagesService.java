package com.qiashe.service;

import com.qiashe.domain.FacilityItem;
import com.qiashe.domain.Image;

import java.util.List;

public interface ImagesService {
    int uploadImage(Image image);
    List<Image> findImgByCIName(String name);
    List<Image> findImgByCIId(Integer CIId);
    Image findImgById(Integer imageId);
    List<Image> FindAll();
    int RemoveItem(Integer id);
}
