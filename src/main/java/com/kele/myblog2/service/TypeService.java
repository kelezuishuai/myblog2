package com.kele.myblog2.service;

import com.kele.myblog2.entity.Type;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/12
 * @Content:
 */
public interface TypeService {

    int saveType(Type type);

    //通过id拿到分类的名称
    Type getType(Long id);

    List<Type> getAllType();

    Type getTypeByName(String name);

    //修改分类名称
    int updateType(Type type);

    //删除分类
    void deleteType(Long id);

    List<Type> getAllTypeAndBlog();
}
