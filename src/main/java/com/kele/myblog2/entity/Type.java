package com.kele.myblog2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类实体类
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/12
 * @Content:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();
}
