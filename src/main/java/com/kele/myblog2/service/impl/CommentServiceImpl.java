package com.kele.myblog2.service.impl;

import com.kele.myblog2.entity.Comment;
import com.kele.myblog2.mapper.BlogMapper;
import com.kele.myblog2.mapper.CommentMapper;
import com.kele.myblog2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/26
 * @Content:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();


    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //查询出父节点
        List<Comment> comments = commentMapper.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for(Comment comment : comments){
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentMapper.findByBlogIdParentIdNotNull(blogId,id);
//            查询出子评论
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
//        判断是否有一级子评论
        if(childComments.size() > 0){
//                循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
//                    查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    private void recursively(Long blogId, Long childId, String parentNickname1) {
//        根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findByBlogIdAndReplayId(blogId,childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId,replayId,parentNickname);
            }
        }
    }

    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentMapper.saveComment(comment);
//        文章评论计数
        blogMapper.getCommentCountById(comment.getBlogId());
        return comments;
    }

    @Override
    public void deleteComment(Comment comment, Long id) {

        commentMapper.deleteComment(id);
        blogMapper.getCommentCountById(comment.getBlogId());
    }
}
