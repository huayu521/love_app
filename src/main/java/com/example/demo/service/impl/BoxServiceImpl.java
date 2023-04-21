package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.BbBoxDomain;
import com.example.demo.domain.BbImageDomain;
import com.example.demo.domain.BbPaperDomain;
import com.example.demo.mapper.BbBoxsMapper;
import com.example.demo.mapper.BbImageMapper;
import com.example.demo.mapper.BbPaperMapper;
import com.example.demo.service.BoxService;
import com.example.demo.tools.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Service
public class BoxServiceImpl extends ServiceImpl<BbBoxsMapper,BbBoxDomain> implements BoxService {

    @Autowired
    BbBoxsMapper bbBoxMapper;

    @Autowired
    BbPaperMapper bbPaperMapper;

    @Autowired
    BbImageMapper bbImageMapper;

    @Override
    public List<BbBoxDomain> getList(int userId) {
        List<BbBoxDomain> list = bbBoxMapper.findUserAll(userId);

        return list;
    }

    @Override
    public List<BbPaperDomain> getOneList(int userId) {

        List<BbPaperDomain> list = bbPaperMapper.findOne(userId);

        return list;
    }

    @Override
    public boolean addBox(BbBoxDomain bbBoxDomain, String order_no) {
        //订单编号
        bbBoxDomain.setOrder_no(order_no);

        bbBoxDomain.setCreate_time(new Date());
        bbBoxDomain.setStatus(1);
        //订单号
        bbBoxDomain.setType(0);

        boolean bool = bbBoxMapper.addBox(bbBoxDomain);

        BbImageDomain bbImageDomain = new BbImageDomain();

        //图片添加到数据库
        for (int i = 0; i < bbBoxDomain.getImg().length; i++) {

            bbImageDomain.setBox_id(bbBoxDomain.getBox_id());
            bbImageDomain.setUrl(bbBoxDomain.getImg()[i]);
            bbImageDomain.setCreate_time(new Date());

            bbImageMapper.addImg(bbImageDomain);
        }

        return bool;
    }

    @Override
    public boolean delBox(int box_id) {
        return bbBoxMapper.delBox(box_id);
    }

    @Override
    public boolean editStatus(int box_id,int status,String remark) {
        return bbBoxMapper.editStatus(box_id,status,remark);
    }
}
