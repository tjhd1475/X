package com.qiashe.service.impl;

import com.qiashe.dao.CanteenItemsDao;
import com.qiashe.dao.FacilityItemsDao;
import com.qiashe.domain.CanteenItem;
import com.qiashe.domain.FacilityItem;
import com.qiashe.service.CanteenItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanteenItemsServiceImpl implements CanteenItemsService {
    @Autowired
    private CanteenItemsDao canteenItemsDao;
    @Autowired
    private FacilityItemsDao facilityItemsDao;
    @Override
    public int AddCanteenItem(CanteenItem canteenItem) {
        int num=0;
        CanteenItem oldCtI=canteenItemsDao.selectCanteenItemByName(canteenItem.getName());
        if(oldCtI==null||oldCtI.getFacilityItemId()!=canteenItem.getFacilityItemId()){
            num=canteenItemsDao.InsertCanteenItem(canteenItem);
        }else {
            num=canteenItemsDao.updateCanteenItem(canteenItem,canteenItem.getName());
        }
        float price=facilityItemsDao.selectPriceAVG(canteenItem.getFacilityItemId());
        facilityItemsDao.updatePrice(canteenItem.getFacilityItemId(),price);
        return num;
    }

    @Override
    public List<CanteenItem> FindAll() {
        List<CanteenItem> canteenItems= canteenItemsDao.selectAll();
        return canteenItems;
    }

    @Override
    public List<CanteenItem> FindByFI(Integer facilityItemId) {
        List<CanteenItem> canteenItems= canteenItemsDao.selectByFI(facilityItemId);
        return  canteenItems;
    }

    @Override
    public int RemoveItem(Integer id) {
        int num=canteenItemsDao.DeleteItem(id);
        return num;
    }

    @Override
    public List<CanteenItem> FindCanteenItemByNameAlike(String name) {
        List<CanteenItem> canteenItems= canteenItemsDao.selectCanteenItemByNameLike(name);
        if(canteenItems==null)
            System.out.println("===找不到1===");
        return canteenItems;
    }

    @Override
    public CanteenItem FindCanteenItemByName(String name) {
        CanteenItem canteenItem=canteenItemsDao.selectCanteenItemByName(name);
        if(canteenItem==null)
            System.out.println("===找不到2===");
        return canteenItem;
    }

    @Override
    public CanteenItem FindItemById(Integer id) {
        CanteenItem canteenItem= canteenItemsDao.selectByCI(id);
        if(canteenItem==null)
            System.out.println("===找不到3===");
        return canteenItem;
    }
}
