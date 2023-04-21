package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.BbBoxDomain;
import com.example.demo.domain.BbNotesDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提现记录表
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Mapper
public interface BbNotesMapper extends BaseMapper<BbNotesDomain> {
    /**
     * 添加记录
     *
     * @param bbNotesDomain
     */
    boolean addNotes(BbNotesDomain bbNotesDomain);

    /**
     * 添加记录
     *
     * @param userId
     */
    List<BbNotesDomain> withdrawalList(int userId);
}
