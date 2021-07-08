package com.qiashe.dao;


import com.qiashe.domain.FI_Avg_Result;
import com.qiashe.domain.FacilityItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FacilityItemsDao {
    int InsertFacilityItem(FacilityItem facilityItem);
    List<FacilityItem> selectFacilityItemByNameLike(String title);
    FacilityItem selectFacilityItemByName(String title);
    int updateFacilityItem(@Param("new") FacilityItem facilityItem, @Param("oldTitle") String title);
    List<FacilityItem> selectAll();
    int DeleteItem(Integer id);
    FacilityItem selectItemById(Integer id);
    float selectPriceAVG(Integer FIId);
    float selectMarkAVG(Integer FIId);
    int updatePrice(@Param("FIId") Integer FIId,@Param("price") Float price);
    List<FI_Avg_Result> selectAllMarkAVG();
}
