package com.kele.myblog2.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 友链实体类
 * @DATE:2020年9月12日15:56:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendLink {

    private Long id;
    private String blogname;
    private String blogaddress;
    private String pictureaddress;
    private Date createTime;

}