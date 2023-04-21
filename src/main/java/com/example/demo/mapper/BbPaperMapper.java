package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.BbBoxDomain;
import com.example.demo.domain.BbPaperDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 纸条
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Mapper
public interface BbPaperMapper extends BaseMapper<BbPaperDomain> {
    /**
     * 查询我收取到的纸条表
     * @param userId
     * */
    List<BbPaperDomain> findOne(int userId);
}
