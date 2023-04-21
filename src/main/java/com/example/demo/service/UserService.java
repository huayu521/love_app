package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.BbNotesDomain;
import com.example.demo.domain.BbUserDomain;

import java.util.List;

/**
 * 用户接口
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
public interface UserService extends IService<BbUserDomain> {
    /**
     * 注册用户
     *
     * @param bbUserDomain
     */
    int register(BbUserDomain bbUserDomain);

    /**
     * 登录用户
     *
     * @param bbUserDomain
     */


    JSONObject login(String openid);

    /**
     * 用户详细信息
     *
     * @param userId
     */
    BbUserDomain userInfo(int userId);

    /**
     * 用户提现
     *
     * @param userId
     * @param moeny
     */
    boolean withdrawal(int userId, Object moeny);

    /**
     * 提现列表
     *
     * @param userId
     */
    List<BbNotesDomain> withdrawalList(int userId);
}
