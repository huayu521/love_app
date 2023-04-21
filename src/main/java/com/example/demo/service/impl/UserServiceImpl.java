package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.BbNotesDomain;
import com.example.demo.domain.BbUserDomain;
import com.example.demo.mapper.BbNotesMapper;
import com.example.demo.mapper.BbUserMapper;
import com.example.demo.service.UserService;
import com.example.demo.tools.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Service
public class UserServiceImpl extends ServiceImpl<BbUserMapper,BbUserDomain> implements UserService {

    @Autowired
    BbUserMapper bbUserMapper;

    @Autowired
    BbNotesMapper bbNotesMapper;

    @Override
    public int register(BbUserDomain bbUserDomain) {
        BbUserDomain find = bbUserMapper.findOneUser(bbUserDomain.getOpenid());

        if (find == null) {
            //注册用户信息进入数据库
            bbUserDomain.setCreate_time(new Date());

            boolean bool = bbUserMapper.addUser(bbUserDomain);

            return 200;
        }

        return 1006;
    }

    @Override
    public JSONObject login(String openid) {
        BbUserDomain find = bbUserMapper.findOneUser(openid);
        JSONObject userData=new JSONObject();
        if (find!=null) {
            String token = Token.createToken(find.getUserId());
            userData.put("token",token);
            userData.put("phone",find.getPhone());
            userData.put("avatar",find.getAvatar());
            userData.put("nickname",find.getNickname());
            userData.put("InferiorMatch",find.getInferiorMatch());
            userData.put("downMatch",find.getDownMatch());
            userData.put("type",find.getType());
            userData.put("openid",find.getOpenid());

            return userData;
        }

        return userData;


    }

    @Override
    public BbUserDomain userInfo(int userId) {
        BbUserDomain info = bbUserMapper.userInfo(userId);

        return info;
    }

    @Transactional(isolation = Isolation.DEFAULT, timeout = 3000)
    @Override
    public boolean withdrawal(int userId, Object moeny) {
        try {
            //先扣除用户的提现余额
            boolean bool = bbUserMapper.subtract(userId, moeny);

            if (bool) {
                //添加数据到提现记录表
                BbNotesDomain bbNotesDomain = new BbNotesDomain();

                bbNotesDomain.setUser_id(userId);

                bbNotesDomain.setMoney(String.valueOf(moeny));

                bbNotesDomain.setType(1);

                bbNotesDomain.setCreateTime(new Date());


                bbNotesMapper.addNotes(bbNotesDomain);

                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<BbNotesDomain> withdrawalList(int userId) {
        return bbNotesMapper.withdrawalList(userId);
    }
}
