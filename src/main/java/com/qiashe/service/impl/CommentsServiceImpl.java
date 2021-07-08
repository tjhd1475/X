package com.qiashe.service.impl;

import com.qiashe.dao.CanteenItemsDao;
import com.qiashe.dao.CommentsDao;
import com.qiashe.domain.Comment;
import com.qiashe.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsDao commentsDao;
    @Autowired
    private CanteenItemsDao canteenItemsDao;
    @Override
    public int addComment(Comment comment) {
        if(canteenItemsDao.selectByCI(comment.getCanteenItemId())==null){
            return 0;
        }
        Comment find=commentsDao.selectByUserIdAndCIId(comment.getUserId(),comment.getCanteenItemId());
        int num=0;
        if(find!=null){
            if(comment.getContent()!=""&&comment.getContent()!=null){
                find.setContent(comment.getContent());
            }
            if(comment.getIsLike()!=-1){
                find.setIsLike(comment.getIsLike());
            }
            if(comment.getMark()!=-1){
                find.setMark(comment.getMark());
            }
            num=commentsDao.updateByCIId(find);
        }else{
            if(comment.getIsLike()==-1){
                comment.setIsLike(0);
            }
            if(comment.getContent()==null){
                comment.setContent("");
            }
            num= commentsDao.insertComments(comment);

        }
        Float mark=commentsDao.selectCIAVG(comment.getCanteenItemId());
        canteenItemsDao.updateMark(comment.getCanteenItemId(),mark);
        return num;
    }

    @Override
    public List<Comment> findCommentsByCIId(Integer CIId) {
        List<Comment> comments=commentsDao.selectByCIId(CIId);
        return comments;
    }

    @Override
    public List<Comment> findAll() {
        List<Comment> comments=commentsDao.selectAll();
        return comments;
    }

    @Override
    public int removeById(Integer id) {
        int num= commentsDao.deleteById(id);
        return num;
    }

    @Override
    public Comment findUserComment(Integer userId, Integer CIId) {
        Comment comment= commentsDao.selectByUserIdAndCIId(userId,CIId);
        return comment;
    }

    @Override
    public Map<String, String> findMLC(Integer CIId) {
        float mark=-1;
        if(commentsDao.selectMarkCNT(CIId)!=0)
            mark=commentsDao.selectCIAVG(CIId);
        int CntComment= commentsDao.selectCommentsCNT(CIId);
        int CntLike=commentsDao.selectLikeCNT(CIId);
        Map<String,String> map=new HashMap<>();
        map.put("mark",String.valueOf(mark));
        map.put("CntComment",String.valueOf(CntComment));
        map.put("CntLike",String.valueOf(CntLike));
        return map;
    }
}
