package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.BbBoxDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 盒子数据类
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Mapper
public interface BbBoxsMapper extends BaseMapper<BbBoxDomain> {
    /**
     * 查询单个用户全部数据
     * 1对多图片
     *
     * @param id
     */
    List<BbBoxDomain> findUserAll(int id);


    /**
     * 添加数据
     * 添加纸条
     *
     * @param bbBoxDomain
     */
    boolean addBox(BbBoxDomain bbBoxDomain);

    /**
     * 删除
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
