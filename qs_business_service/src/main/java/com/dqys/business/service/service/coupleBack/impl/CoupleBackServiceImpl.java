package com.dqys.business.service.service.coupleBack.impl;

import com.dqys.business.orm.mapper.coupleBack.CoupleBackMapper;
import com.dqys.business.orm.pojo.coupleBack.CoupleBack;
import com.dqys.business.orm.pojo.coupleBack.CoupleBackMessage;
import com.dqys.business.orm.pojo.coupleBack.dto.CoupleBackDTO;
import com.dqys.business.service.service.coupleBack.CoupleBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/11/16.
 */
@Service
public class CoupleBackServiceImpl implements CoupleBackService {

    @Autowired
    private CoupleBackMapper coupleBackMapper;

    @Override
    public Map addCoupleBack(CoupleBack coupleBack) {
        Map map = new HashMap<>();
        map.put("result", "no");
        if (coupleBackMapper.addCoupleBack(coupleBack) > 0) {
            map.put("result", "yes");
        } else {
            map.put("msg", "添加反馈信息失败，请稍后再试！");
        }
        return map;
    }

    @Override
    public Map delCoupleBack(Integer id) {
        Map map = new HashMap<>();
        map.put("result", "no");
        if (coupleBackMapper.delCoupleBack(id) > 0) {
            map.put("result", "yes");
        } else {
            map.put("msg", "删除反馈信息失败，请稍后再试！");
        }
        return map;
    }

    @Override
    public Map addMessage(CoupleBackMessage message) {
        Map map = new HashMap<>();
        map.put("result", "no");
        if (coupleBackMapper.addMessage(message) > 0) {
            map.put("result", "yes");
        } else {
            map.put("msg", "添加留言信息失败，请稍后再试！");
        }
        return map;
    }

    @Override
    public Map delMessage(Integer id) {
        Map map = new HashMap<>();
        map.put("result", "no");
        if (coupleBackMapper.delMessage(id) > 0) {
            map.put("result", "yes");
        } else {
            map.put("msg", "删除留言信息失败，请稍后再试！");
        }
        return map;
    }

    @Override
    public Map listBack(CoupleBackDTO coupleBackDTO) {
        Map map = new HashMap<>();
        map.put("result", "yes");
        List<CoupleBackDTO> list = coupleBackMapper.selectBackByDTO(coupleBackDTO);
        map.put("list", list);
        return map;
    }

    @Override
    public Map listMessage(Integer tcbId) {
        Map map = new HashMap<>();
        map.put("result", "yes");
        map.put("list", coupleBackMapper.listMessage(tcbId));
        return map;
    }
}
