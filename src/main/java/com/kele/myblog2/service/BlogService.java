package com.kele.myblog2.service;

import com.kele.myblog2.entity.Blog;
import com.kele.myblog2.queryvo.*;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/12
 * @Content:
 */
public interface BlogService {

    //拿到博客id到后面修改删除用
    ShowBlog getBlogById(Long id);

    //修改博客
    int updateBlog(ShowBlog showBlog);

    //删除博客
    void deleteBlog(Long id);

    //查找所有博客
    List<BlogQuery> getAllBlog();

    //保存博客
    int saveBlog(Blog blog);


    //搜索博客
    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    List<FirstPageBlog> getAllFirstPageBlog();

    List<RecommendBlog> getRecommendedBlog();

//    List<FirstPageBlog> getNewBlog();

    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id) throws NotFoundException;

    //根据TypeId获取博客列表，在分类页进行的操作
    List<FirstPageBlog> getByTypeId(Long typeId);

    Integer getBlogTotal();

    Integer getBlogViewTotal();

    Integer getBlogCommentTotal();

    Integer getBlogMessageTotal();

}
