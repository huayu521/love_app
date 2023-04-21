package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 图片
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Setter
@Getter
public class BbImageDomain {
    @TableId(type = IdType.AUTO)
    private int image_id;

    /**
     * 地址
     */
    private String url;

    /**
     * 盒子id
     */
    private int box_id;


    private Date create_time;
}
