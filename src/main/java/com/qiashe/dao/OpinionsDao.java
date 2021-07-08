package com.qiashe.dao;

import com.qiashe.domain.Opinion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpinionsDao {
    int insertOpinion(Opinion opinion);
    List<Opinion> selectAll();
    Opinion selectById(Integer id);
    int updateById(@Param("opinion") Opinion opinion);
}
