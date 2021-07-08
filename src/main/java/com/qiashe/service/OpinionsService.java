package com.qiashe.service;

import com.qiashe.domain.Opinion;

import java.util.List;

public interface OpinionsService {
    int addOpinion(Opinion opinion);
    List<Opinion> findAllOpinions();
    Opinion findOpinionById(Integer id);
    int solveOpinion(Opinion opinion);
}
