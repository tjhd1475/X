package com.qiashe.service;

import com.qiashe.domain.CanteenItem;
import com.qiashe.domain.FI_Avg_Result;
import com.qiashe.domain.FacilityItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FacilityItemsService {
    int AddFacilityItem(FacilityItem facilityItem);
    List<FacilityItem> FindFacilityItemByNameAlike(String title);
    FacilityItem FindFacilityItemByName(String title);
    List<FacilityItem> FindAll();
    int RemoveItem(Integer id);
    FacilityItem findItem(Integer id);
    Map<String,Float> findAvgPriceAndMark(Integer id);
    List<FI_Avg_Result> findAllAvg();
}
