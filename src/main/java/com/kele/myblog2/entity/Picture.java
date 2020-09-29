package com.kele.myblog2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 照片墙实体类
 * @Author: kele
 * @DATE:2020年9月12日15:56:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Picture {

    private Long id;
    private String picturename;
    private String picturetime;
    private String pictureaddress;
    private String picturedescription;


}