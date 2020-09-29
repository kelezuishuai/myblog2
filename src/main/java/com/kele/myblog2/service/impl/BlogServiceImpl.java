package com.kele.myblog2.service.impl;

import com.kele.myblog2.entity.Blog;
import com.kele.myblog2.mapper.BlogMapper;
import com.kele.myblog2.queryvo.*;
import com.kele.myblog2.service.BlogService;
import com.kele.myblog2.util.MarkdownUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/12
 * @Content:
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    /**
    通过id拿到要修改的文章
     */
    @Override
    public ShowBlog getBlogById(Long id) {
        return blogMapper.getBlogById(id);

    }
    /**
     * 修改文章
     * @param showBlog
     * @return
     */
    @Override
    public int updateBlog(ShowBlog showBlog) {
        return blogMapper.updateBlog(showBlog);
    }

    @Override
    public void deleteBlog(Long id) {

        blogMapper.deleteBlog(id);

    }
    /**
     * 查询所有博客
     * @return
     */
    @Override
    public List<BlogQuery> getAllBlog() {
        List<BlogQuery> allBlogQuery = blogMapper.getAllBlogQuery();
        return allBlogQuery;
    }
    /**
     * 保存博客
     * @param blog
     * @return
     */
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        int i = blogMapper.saveBlog(blog);
        return i;
    }

    /**
     * 模糊查询
     * @param searchBlog
     * @return
     */
    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogMapper.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogMapper.getFirstPageBlog();
    }

    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        List<RecommendBlog> recommendBlog = blogMapper.getAllRecommendBlog();
        return recommendBlog;
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) throws NotFoundException {
        System.out.println(id);
        DetailedBlog detailedBlog = blogMapper.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
//        文章访问数量自增
        blogMapper.updateViews(id);
//        文章评论数量更新
        blogMapper.getCommentCountById(id);
        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }

    @Override
    public Integer getBlogTotal() {
        return blogMapper.getBlogTotal();
    }

    @Override
    public Integer getBlogViewTotal() {
        return blogMapper.getBlogViewTotal();
    }

    @Override
    public Integer getBlogCommentTotal() {
        return blogMapper.getBlogCommentTotal();
    }

    @Override
    public Integer getBlogMessageTotal() {
        return blogMapper.getBlogMessageTotal();
    }


}
