package com.qiashe.dao;

import com.qiashe.domain.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsDao {
    int insertComments(Comment comment);
    List<Comment> selectByCIId(Integer CIId);
    Comment selectByUserIdAndCIId(@Param("userId") Integer userId, @Param("CIId") Integer CIId);
    int updateByCIId(Comment comment);
    List<Comment> selectAll();
    int deleteById(Integer id);
    float selectCIAVG(Integer CIId);
    int selectCommentsCNT(Integer CIId);
    int selectLikeCNT(Integer CIId);
    int selectMarkCNT(Integer CIId);
}
