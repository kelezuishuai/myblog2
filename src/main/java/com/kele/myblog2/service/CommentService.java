package com.kele.myblog2.service;

import com.kele.myblog2.entity.Comment;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/26
 * @Content:
 */
public interface CommentService {

    //所有评论
    List<Comment> listCommentByBlogId(Long blogId);

    //保存评论
    int saveComment(Comment comment);

//    查询子评论
//    List<Comment> getChildComment(Long blogId,Long id);

    //删除评论
    void deleteComment(Comment comment,Long id);
}
