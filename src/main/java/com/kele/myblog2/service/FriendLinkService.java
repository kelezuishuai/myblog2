package com.kele.myblog2.service;

import com.kele.myblog2.entity.FriendLink;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/24
 * @Content:
 */
public interface FriendLinkService {

    //查询所有友链
    List<FriendLink> listFriendLink();

    //添加链接
    int saveFriendLink(FriendLink friendLink);

    //通过博客地址查询链接
    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    //通过id拿到链接内容方便删除修改
    FriendLink getFriendLink(Long id);

    //修改连接
    int updateFriendLink(FriendLink friendLink);

    //删除链接
    void deleteFriendLink(Long id);

}
