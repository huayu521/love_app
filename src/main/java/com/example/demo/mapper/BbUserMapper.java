package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.BbBoxDomain;
import com.example.demo.domain.BbUserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 注册
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Mapper
public interface BbUserMapper extends BaseMapper<BbUserDomain> {
    /**
     * 查询单个手机号是否存在数据表
     *
     * @param phone
     * @return BbUserDomain
     */
    BbUserDomain findOneUser(String phone);


    /**
     * 添加用户数据
     *
     * @param bbUserDomain
     */
    boolean addUser(BbUserDomain bbUserDomain);

    /**
     * 查询用户信息
     *
     * @param userId
     */
    BbUserDomain userInfo(int userId);

    /**
     * 余额提现
     *
     * @param money
     * @param userId
     */
    boolean subtract(int userId, Object money);
}
