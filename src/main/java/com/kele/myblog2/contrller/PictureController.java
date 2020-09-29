package com.kele.myblog2.contrller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kele.myblog2.entity.Picture;
import com.kele.myblog2.service.impl.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/admin")
public class PictureController {

    @Autowired
    private PictureServiceImpl pictureService;

    /**
     * 查询照片
     * @param model
     * @param pageNum
     * @return
     */
    @GetMapping("/pictures")
    public String pictures(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Picture> listFriendLink = pictureService.listPicture();
        PageInfo<Picture> pageInfo = new PageInfo<Picture>(listFriendLink);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/pictures";
    }

    /**
     * 跳转新增页面
     * @param model
     * @return
     */
    @GetMapping("/pictures/input")
    public String toInput(Model model) {
        model.addAttribute("picture", new Picture());
        return "admin/pictures-input";
    }

    /**
     * 照片新增
     * @param picture
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/pictures")
    public String Insert(@Validated Picture picture, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            return "admin/pictures-input";
        }

        int P = pictureService.savePicture(picture);
        if (P == 0 ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/pictures";
    }

    /**
     * 跳转照片编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/pictures/{id}/input")
    public String toEditInput(@PathVariable Long id, Model model) {
        model.addAttribute("picture", pictureService.getPicture(id));
        return "admin/pictures-input";
    }

    /**
     * 编辑相册
     * @param picture
     * @param attributes
     * @return
     */
    @PostMapping("/pictures/{id}")
    public String edit(@Validated Picture picture, RedirectAttributes attributes) {

        int P = pictureService.updatePicture(picture);
        if (P == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/pictures";
    }

    /**
     * 删除照片
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/pictures/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        pictureService.deletePicture(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/pictures";
    }

}
