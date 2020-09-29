package com.kele.myblog2.contrller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kele.myblog2.entity.Type;
import com.kele.myblog2.queryvo.FirstPageBlog;
import com.kele.myblog2.service.impl.BlogServiceImpl;
import com.kele.myblog2.service.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 分类页面显示控制器
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private BlogServiceImpl blogService;

//    分页查询分类
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {
        List<Type> types = typeService.getAllTypeAndBlog();
        System.out.println("types:"+types);
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);

        PageHelper.startPage(pageNum, 10000);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

}