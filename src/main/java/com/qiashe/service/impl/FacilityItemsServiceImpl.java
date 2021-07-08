package com.qiashe.service.impl;

import com.qiashe.dao.FacilityItemsDao;
import com.qiashe.domain.CanteenItem;
import com.qiashe.domain.FI_Avg_Result;
import com.qiashe.domain.FacilityItem;
import com.qiashe.service.FacilityItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacilityItemsServiceImpl implements FacilityItemsService {
    @Autowired
    private FacilityItemsDao facilityItemsDao;
    @Override
    public int AddFacilityItem(FacilityItem facilityItem) {
        int num=0;
        FacilityItem oldFyI=facilityItemsDao.selectFacilityItemByName(facilityItem.getTitle());
        if(oldFyI==null) {
            facilityItemsDao.InsertFacilityItem(facilityItem);
        }else {
            facilityItemsDao.updateFacilityItem(facilityItem, facilityItem.getTitle());
        }
        return num;
    }
    @Override
    public List<FacilityItem> FindFacilityItemByNameAlike(String title) {
        List<FacilityItem> facilityItems= facilityItemsDao.selectFacilityItemByNameLike(title);
        if(facilityItems==null)
            System.out.println("===找不到3===");
        return facilityItems;
    }

    @Override
    public FacilityItem FindFacilityItemByName(String title) {
        FacilityItem facilityItem=facilityItemsDao.selectFacilityItemByName(title);
        if(facilityItem==null)
            System.out.println("===找不到4===");
        return facilityItem;
    }

    @Override
    public List<FacilityItem> FindAll() {
        List<FacilityItem> facilityItems= facilityItemsDao.selectAll();
        return  facilityItems;
    }

    @Override
    public int RemoveItem(Integer id) {
        int num=facilityItemsDao.DeleteItem(id);
        return num;
    }

    @Override
    public FacilityItem findItem(Integer id) {
        FacilityItem facilityItem=facilityItemsDao.selectItemById(id);
        return  facilityItem;
    }

    @Override
    public Map<String, Float> findAvgPriceAndMark(Integer id) {
        if(facilityItemsDao.selectItemById(id)==null){
            return null;
        }
        float mark= facilityItemsDao.selectMarkAVG(id);
        float price=facilityItemsDao.selectItemById(id).getPrice();
        Map<String,Float> res=new HashMap<>();
        res.put("mark",mark);
        res.put("price",price);
        return res;
    }

    @Override
    public List<FI_Avg_Result> findAllAvg() {
        List<FI_Avg_Result> res= facilityItemsDao.selectAllMarkAVG();
        return res;
    }
}
