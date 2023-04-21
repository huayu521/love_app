package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 回调通知
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Controller
public class NotifyController {
    /**
     * 支付成功回调 订单号加入处理支付成功的队列
     * */
    @GetMapping("/notifyOrderQueue")
    public void notifyOrderQueue(){

    }
}
