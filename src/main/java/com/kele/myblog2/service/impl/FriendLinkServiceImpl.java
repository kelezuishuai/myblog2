package com.kele.myblog2.service.impl;

import com.kele.myblog2.entity.FriendLink;
import com.kele.myblog2.mapper.FriendLinksMapper;
import com.kele.myblog2.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/24
 * @Content:
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinksMapper linksMapper;

    /**
     * 查询所有连接
     * @return
     */
    @Override
    public List<FriendLink> listFriendLink() {
        return linksMapper.getAllLinks();
    }

    /**
     * 新增链接
     * @param friendLink
     * @return
     */
    @Override
    public int saveFriendLink(FriendLink friendLink) {
        return linksMapper.saveFriendLink(friendLink);
    }

    /**
     * 通过博客地址查询连接
     * @param blogaddress
     * @return
     */
    @Override
    public FriendLink getFriendLinkByBlogaddress(String blogaddress) {
        return linksMapper.getFriendLinkByBlogaddress(blogaddress);
    }

    /**
     * 通过id拿到连接内容
     * @param id
     * @return
     */
    @Override
    public FriendLink getFriendLink(Long id) {
        return linksMapper.getFriendLink(id);
    }

    /**
     * 修改链接
     * @param friendLink
     * @return
     */
    @Override
    public int updateFriendLink(FriendLink friendLink) {
        return linksMapper.updateFriendLink(friendLink);
    }

    /**
     * 删除链接
     * @param id
     */
    @Override
    public void deleteFriendLink(Long id) {

        linksMapper.deleteFriendLink(id);
    }
}
