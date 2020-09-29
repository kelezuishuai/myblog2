package com.kele.myblog2.mapper;

import com.kele.myblog2.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/12
 * @Content:
 */
@Mapper
@Component
public interface TypeMapper {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    //根据名字来查询分类名称
    Type getTypeByName(String name);

    //修改分类
    int updateType(Type type);

    //删除分类
    void deleteType(Long id);

    List<Type> getAllTypeAndBlog();


}
