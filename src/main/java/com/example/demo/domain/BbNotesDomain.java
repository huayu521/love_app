package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 记录表
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Setter
@Getter
public class BbNotesDomain {
    @TableId(type = IdType.AUTO)
    private int notes_id;

    /**
     * 用户id
     */
    private int user_id;

    /**
     * 金额
     */
    private String money;

    /**
     * 1提现 2平台赠送
     */
    private int type;

    /**
     * 提现审核 1通过 2驳回
     */
    private int notes_type;

    /**
     * 驳回原因
     */
    private String remard;

    private Date createTime;
}
