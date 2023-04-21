package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Setter
@Getter
public class BbUserDomain {
    @TableId(type = IdType.AUTO)
    private int userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 奖励金
     */
    private float rewards;

    /**
     * 提现金额
     */
    private int total;

    /**
     * 接收验证码
     */
    private String code;

    /**
     * 密码
     */
    private String pwd;


    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 下下级红娘
     */
    private int InferiorMatch;

    /**
     * 下级红娘数
     */
    private int downMatch;

    /**
     * 推广用户数
     */
    private int userNum;

    /**
     * 0普通用户 1审核员
     */
    private int type;

    /**
     * 微信openid
     */
    private String openid;

    private Date create_time;
}
