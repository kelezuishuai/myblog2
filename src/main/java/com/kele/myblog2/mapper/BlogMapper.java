package com.kele.myblog2.mapper;

import com.kele.myblog2.entity.Blog;
import com.kele.myblog2.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/12
 * @Content:
 */
@Mapper
@Component
public interface BlogMapper {

    //保存
    int saveBlog(Blog blog);

    //查询所有博客
    List<Blog> getAllBlog();
    List<BlogQuery> getAllBlogQuery();

    int updateBlog(ShowBlog showBlog);

    ShowBlog getBlogById(Long id);

    void deleteBlog(Long id);

    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    List<FirstPageBlog> getFirstPageBlog();

    List<RecommendBlog> getAllRecommendBlog();

//    List<FirstPageBlog> getNewBlog();

    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id);

    int updateViews(Long id);

    //    根据博客id查询出评论数量
    int getCommentCountById(Long id);

    List<FirstPageBlog> getByTypeId(Long typeId);

    Integer getBlogTotal();

    Integer getBlogViewTotal();

    Integer getBlogCommentTotal();

    Integer getBlogMessageTotal();

}
