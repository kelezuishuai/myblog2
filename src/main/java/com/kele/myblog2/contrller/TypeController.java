package com.kele.myblog2.contrller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kele.myblog2.entity.Type;
import com.kele.myblog2.service.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/22
 * @Content:
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeServiceImpl typeService;

    /**
     * 分页查询分类列表
     * @param model
     * @param pageNum
     * @return
     */
    @GetMapping("/types")
    public String list(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){

        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";

    }

    /**
     * 到新增分类页面
     */
    @GetMapping("/types/input")
    public String toInput(Model model){

        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @RequestMapping("/types")
    public String input(@Validated Type type, RedirectAttributes attributes){

        Type type1 = typeService.getTypeByName(type.getName());

        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.saveType(type);
        if (t == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";


    }

    /**
     * 跳转到修改分类的页面
     */
    @GetMapping("/types/{id}/input")
    public String toUpdate(@PathVariable("id") Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    /**
     * 修改连接
     * @param type
     * @param attributes
     * @return
     */
    @PostMapping("/types/{id}")
    public String edit(@Validated Type type, RedirectAttributes attributes){

        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.updateType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/types";
    }

    //    删除分类
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
