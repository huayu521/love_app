package com.example.demo.service;

/**
 * 支付
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
public interface PayService {
    /**
     * 调用发起支付
     * @param out_trade_no
     * */
    String payOrders(String out_trade_no);
}
