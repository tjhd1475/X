package com.qiashe.service;

import com.qiashe.domain.Comment;

import java.util.List;
import java.util.Map;

public interface CommentsService {
    int addComment(Comment comment);
    List<Comment> findCommentsByCIId(Integer CIId);
    List<Comment> findAll();
    int removeById(Integer id);
    Comment findUserComment(Integer userId,Integer CIId);
    Map<String,String> findMLC(Integer CIId);
}
