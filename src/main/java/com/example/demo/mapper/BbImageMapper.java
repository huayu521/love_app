package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.BbBoxDomain;
import com.example.demo.domain.BbImageDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 图片
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Mapper
public interface BbImageMapper extends BaseMapper<BbImageDomain> {
    /**
     * 查询box_id下面的图片
     *
     * @param box_id
     */
    List<BbImageDomain> findImgAll(int box_id);

    /**
     * 添加图片
     *
     * @param bbImageDomain
     */
    boolean addImg(BbImageDomain bbImageDomain);
}
