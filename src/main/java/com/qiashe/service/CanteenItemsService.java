package com.qiashe.service;

import com.qiashe.domain.CanteenItem;
import com.qiashe.domain.FacilityItem;

import java.util.List;

public interface CanteenItemsService {
    int AddCanteenItem(CanteenItem canteenItem);
    List<CanteenItem> FindCanteenItemByNameAlike(String name);
    CanteenItem FindCanteenItemByName(String name);
    CanteenItem FindItemById(Integer id);
    List<CanteenItem> FindAll();
    List<CanteenItem> FindByFI(Integer facilityItemId);
    int RemoveItem(Integer id);
}
