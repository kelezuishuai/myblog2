package com.kele.myblog2.contrller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kele.myblog2.entity.FriendLink;
import com.kele.myblog2.entity.Type;
import com.kele.myblog2.service.impl.FriendLinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/24
 * @Content:
 */
@Controller
@RequestMapping("/admin")
public class FriendLinksController {

    @Autowired
    private FriendLinkServiceImpl friendLinkService;

    /**
     * 查询所有数据
     * @return
     */
    @GetMapping("/friendlinks")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //分页插件进行分页操作
        PageHelper.startPage(pageNum,10);
        List<FriendLink> friendLinks = friendLinkService.listFriendLink();
        PageInfo<FriendLink> pageInfo = new PageInfo<FriendLink>(friendLinks);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/friendlinks";
    }

    /**
     * 跳转到添加页面
     *
     */
    @GetMapping("/friendlinks/input")
    public String toInsert(Model model){
        model.addAttribute("friendlink", new FriendLink());
        return "admin/friendlinks-input";
    }
    /**
     * 添加链接
     */
    @PostMapping("/friendlinks")
    public String insert(@Validated FriendLink friendLink, BindingResult result, RedirectAttributes attributes){

        FriendLink type1 = friendLinkService.getFriendLinkByBlogaddress(friendLink.getBlogaddress());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加相同的网址");
            return "redirect:/admin/friendlinks/input";
        }
        if(result.hasErrors()){
            return "admin/friendlinks-input";
        }
        friendLink.setCreateTime(new Date());
        int F = friendLinkService.saveFriendLink(friendLink);
        if (F == 0 ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/friendlinks";
    }

    /**
     * 到修改页面
     */
    @GetMapping("/friendlinks/{id}/input")
    public String toUpdate(@PathVariable("id") Long id, Model model){
        model.addAttribute("friendlink",friendLinkService.getFriendLink(id));
        return "admin/friendlinks-input";
    }

    /**
     * 修改连接
     */
    @PostMapping("/friendlinks/{id}")
    public String update(@Validated FriendLink friendLink,RedirectAttributes redirectAttributes){

        int i = friendLinkService.updateFriendLink(friendLink);
        if (i == 0 ) {
            redirectAttributes.addFlashAttribute("message", "编辑失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/friendlinks";
    }

    /**
     * 删除友链
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/friendlinks/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        friendLinkService.deleteFriendLink(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/friendlinks";
    }

}
