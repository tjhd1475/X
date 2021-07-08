package com.qiashe.dao;

import com.qiashe.domain.Image;

import java.util.List;

public interface ImagesDao {
    int insertImage(Image image);
    Image selectMainImage(Integer canteenItemId);
    int updateToNotMainImage(Integer imageId);
    List<Image> selectByCIName(String name);
    List<Image> selectByCIId(Integer CIId);
    List<Image> selectAll();
    Image selectImageById(Integer imageId);
    int DeleteItem(Integer id);

}
