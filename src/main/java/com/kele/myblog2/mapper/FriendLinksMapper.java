package com.kele.myblog2.mapper;

import com.kele.myblog2.entity.FriendLink;
import com.kele.myblog2.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/24
 * @Content:
 */
@Mapper
@Component
public interface FriendLinksMapper {

    //查找所有链接
    List<FriendLink> getAllLinks();

    //新增连接
    int saveFriendLink(FriendLink friendLink);

    //通过id拿到链接内容方便删除修改
    FriendLink getFriendLink(Long id);

    //修改连接
    int updateFriendLink(FriendLink friendLink);

    //删除链接
    void deleteFriendLink(Long id);

    //通过博客地址查询链接
    FriendLink getFriendLinkByBlogaddress(String blogaddress);
}
