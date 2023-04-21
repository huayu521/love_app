package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.BbBoxDomain;
import com.example.demo.domain.BbPaperDomain;

import java.util.List;

/**
 * 盒子
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
public interface BoxService extends IService<BbBoxDomain> {
    /**
     * 盒子
     *
     *
     * @param userId
     */
    List<BbBoxDomain> getList(int userId);

    /**
     * 我抽到的
     *
     * @param userId
     */
    List<BbPaperDomain> getOneList(int userId);

    /**
     * 添加纸条
     *
     * @param bbBoxDomain
     */
    boolean addBox(BbBoxDomain bbBoxDomain, String order_no);

    /**
     * 删除纸条
     *
     * @param box_id
     */
    boolean delBox(int box_id);
    /**
     * 修改纸条status
     * @param box_id
     * @param status
     */
    boolean editStatus(int box_id,int status,String remark);
}
