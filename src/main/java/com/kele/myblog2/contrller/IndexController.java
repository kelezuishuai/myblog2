package com.kele.myblog2.contrller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kele.myblog2.entity.Comment;
import com.kele.myblog2.queryvo.DetailedBlog;
import com.kele.myblog2.queryvo.FirstPageBlog;
import com.kele.myblog2.queryvo.RecommendBlog;
import com.kele.myblog2.service.impl.BlogServiceImpl;
import com.kele.myblog2.service.impl.CommentServiceImpl;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/26
 * @Content:
 */
@Controller
public class IndexController {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private CommentServiceImpl commentService;

    @RequestMapping("/")
    public String  toIndex(){

        return "redirect:/index";
    }


    /**
     * 分页查询博客列表
     * @param model
     * @param pageNum
     * @param attributes
     * @return
     */
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                        RedirectAttributes attributes){

        PageHelper.startPage(pageNum,10);
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();

        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("recommendedBlogs", recommendedBlog);

        return "index";
    }

    /**
     * 搜索博客
     * @param model
     * @param pageNum
     * @param query
     * @return
     */
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 1000);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    //    跳转博客详情页面
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) throws NotFoundException {
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }

//    最新博客列表
//    @GetMapping("/footer/newblog")
//    public String newblogs(Model model) {
//        List<FirstPageBlog> newBlog = blogService.getNewBlog();
//        model.addAttribute("newblogs", newBlog);
//        return "index :: newblogList";
//    }

    //    博客信息
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        int blogTotal = blogService.getBlogTotal();
        int blogViewTotal = blogService.getBlogViewTotal();
        int blogCommentTotal = blogService.getBlogCommentTotal();
        int blogMessageTotal = blogService.getBlogMessageTotal();

        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        model.addAttribute("blogMessageTotal",blogMessageTotal);
        return "index :: blogMessage";
    }

}
