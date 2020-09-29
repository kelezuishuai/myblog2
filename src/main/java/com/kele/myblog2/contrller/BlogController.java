package com.kele.myblog2.contrller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kele.myblog2.entity.Blog;
import com.kele.myblog2.entity.Type;
import com.kele.myblog2.entity.User;
import com.kele.myblog2.queryvo.BlogQuery;
import com.kele.myblog2.queryvo.SearchBlog;
import com.kele.myblog2.queryvo.ShowBlog;
import com.kele.myblog2.service.impl.BlogServiceImpl;
import com.kele.myblog2.service.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/12
 * @Content:
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private BlogServiceImpl blogService;

    /**
     * 跳转到博客新增页面
     *
     */
    @GetMapping("/blogs/input")
    public String toinsert(Model model){

        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("blog",new Blog());

        return "admin/blogs-input";
    }


    /**\
     * 博客新增
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        int b = blogService.saveBlog(blog);

        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    //博客列表
    @RequestMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    /**
     * 到博客修改页面
     */

    @GetMapping("/blogs/{id}/input")
    public String editinput(@PathVariable("id") Long id, Model model){

        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("blog",blogById);
        model.addAttribute("types",allType);
        return "/admin/blogs-input";

    }

    /**
     * 博客修改
     */
    @PostMapping("/blogs/{id}")
    public String updateblogs(@Validated ShowBlog showBlog,
                              RedirectAttributes attributes){
        int i = blogService.updateBlog(showBlog);
        if(i==0){
            attributes.addFlashAttribute("message", "修改失败");
        }else
        {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 删除文章
     */

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlogs(@PathVariable("id") Long id,RedirectAttributes attributes){

        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");

        return "redirect:  /admin/blogs";



    }

    //    搜索博客
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        return "/admin/blogs::blogList";
    }



}
