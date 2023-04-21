package com.example.demo.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.WxMaConfiguration;
import com.example.demo.domain.BbUserDomain;
import com.example.demo.mapper.BbUserMapper;
import com.example.demo.service.UserService;
import com.example.demo.tools.Respond;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 微信小程序用户接口
 *
 * @author 尘落
 * @date 2023-04-12
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/wx/user")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMaService wxMaService;


    @Autowired
    private UserService userService;

    @Autowired
    private BbUserMapper bbUserMapper;
    /**
     * 登陆接口
     */
    @PostMapping("/login")
    public Respond login(@RequestBody BbUserDomain bbUserDomain) {
        final WxMaService wxService = wxMaService;

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(bbUserDomain.getCode());
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            BbUserDomain user = bbUserMapper.findOneUser(session.getOpenid());

            if (user == null) {//如果不存在就注册
                bbUserDomain.setOpenid(session.getOpenid());//添加openid
                userService.register(bbUserDomain);
            }
            String str = String.valueOf(userService.login(session.getOpenid()));

            if(!Objects.equals(str, "")){
                return Respond.success(200,"登录成功", str);
            }

            return Respond.error(500,"手机号或密码错误");


//            return Respond.success(200, "成功", session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            // return e.toString();
        }
        return null;
    }
    // @GetMapping("/login")
    // public String login(@PathVariable String appid, String code) {
    //     if (StringUtils.isBlank(code)) {
    //         return "empty jscode";
    //     }
    //
    //     final WxMaService wxService = WxMaConfiguration.getMaService(appid);
    //
    //     try {
    //         WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
    //         this.logger.info(session.getSessionKey());
    //         this.logger.info(session.getOpenid());
    //         //TODO 可以增加自己的逻辑，关联业务相关数据
    //         return JsonUtils.toJson(session);
    //     } catch (WxErrorException e) {
    //         this.logger.error(e.getMessage(), e);
    //         return e.toString();
    //     }
    // }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public Respond info(String sessionKey,
                        String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = wxMaService;

//        // 用户信息校验
//        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
//            return Respond.success(200, "成功", "user check failed");
//        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey.replace(" ","+"), encryptedData.replace(" ","+"),
                iv.replace(" ","+"));

        return Respond.success(200, "成功", userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    public Respond phone(String sessionKey, String signature,
                         String rawData, String encryptedData, String iv) {
        final WxMaService wxService = wxMaService;

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return Respond.success(200, "成功", "user check failed");
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return Respond.success(200, "成功", phoneNoInfo);
    }

}