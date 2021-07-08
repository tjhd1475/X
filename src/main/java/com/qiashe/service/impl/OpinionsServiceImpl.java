package com.qiashe.service.impl;

import com.qiashe.dao.OpinionsDao;
import com.qiashe.domain.Opinion;
import com.qiashe.service.OpinionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionsServiceImpl implements OpinionsService {
    @Autowired
    private OpinionsDao opinionsDao;
    @Override
    public int addOpinion(Opinion opinion) {
        int num=opinionsDao.insertOpinion(opinion);
        return num;
    }

    @Override
    public List<Opinion> findAllOpinions() {
        List<Opinion> opinions=opinionsDao.selectAll();
        return opinions;
    }

    @Override
    public Opinion findOpinionById(Integer id) {
        Opinion opinion=opinionsDao.selectById(id);
        return opinion;
    }

    @Override
    public int solveOpinion(Opinion opinion) {
        int num=opinionsDao.updateById(opinion);
        return num;
    }
}
