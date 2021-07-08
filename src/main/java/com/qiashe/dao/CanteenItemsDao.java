package com.qiashe.dao;

import com.qiashe.domain.CanteenItem;
import com.qiashe.domain.FacilityItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CanteenItemsDao {
    int InsertCanteenItem(CanteenItem canteenItem);
    List<CanteenItem> selectCanteenItemByNameLike(String name);
    CanteenItem selectCanteenItemByName(String name);
    int updateCanteenItem(@Param("new") CanteenItem canteenItem,@Param("oldName") String name);
    List<CanteenItem> selectAll();
    int DeleteItem(Integer id);
    List<CanteenItem> selectByFI(Integer facilityItemId);
    CanteenItem selectByCI(Integer canteenItemId);
    int updateMark(@Param("CIId") Integer CIId,@Param("mark") Float mark);
}
