package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.zcy.*;
import com.dqys.business.orm.pojo.zcy.*;
import com.dqys.business.service.service.ZcyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Service
public class ZcyServiceImpl implements ZcyService {

    @Autowired
    private ZcyEstatesAddressMapper zcyEstatesAddressMapper;
    @Autowired
    private ZcyEstatesFacilityMapper zcyEstatesFacilityMapper;
    @Autowired
    private ZcyEstatesMapper zcyEstatesMapper;
    @Autowired
    private ZcyExpressMapper zcyExpressMapper;
    @Autowired
    private ZcyKeyMapper zcyKeyMapper;
    @Autowired
    private ZcyMaintainMapper zcyMaintainMapper;
    @Autowired
    private ZcyMaintainOtherMapper zcyMaintainOtherMapper;
    @Autowired
    private ZcyMaintainTaxMapper zcyMaintainTaxMapper;
    @Autowired
    private ZcyOwnerContactsMapper zcyOwnerContactsMapper;
    @Autowired
    private ZcyOwnerMapper zcyOwnerMapper;


    @Override
    public Map getEstates(Integer id) {
        Map map = new HashMap<>();
        map.put("estates", zcyEstatesMapper.selectByPrimaryKey(id));
        ZcyEstatesAddress address = new ZcyEstatesAddress();
        address.setEstatesId(id);
        map.put("address", zcyEstatesAddressMapper.selectBySelective(address));//房源地址
        ZcyEstatesFacility zcyEstatesFacility = new ZcyEstatesFacility();
        zcyEstatesFacility.setEstatesId(id);
        zcyEstatesFacility.setType(0);//配套设施
        map.put("facility", zcyEstatesFacilityMapper.selectBySelective(zcyEstatesFacility));
        zcyEstatesFacility.setType(1);//房源特色
        map.put("feature", zcyEstatesFacilityMapper.selectBySelective(zcyEstatesFacility));
        zcyEstatesFacility.setType(2);//房源缺点
        map.put("defect", zcyEstatesFacilityMapper.selectBySelective(zcyEstatesFacility));
        return map;
    }

    @Override
    public Map getOwner(Integer id) {
        Map map = new HashMap<>();
        ZcyOwner zcyOwner = new ZcyOwner();
        zcyOwner.setEstatesId(id);
        List<ZcyOwner> list = zcyOwnerMapper.selectBySelective(zcyOwner);
        if (list.size() > 0) {
            zcyOwner = list.get(0);
            ZcyOwnerContacts zcyOwnerContacts = new ZcyOwnerContacts();
            zcyOwnerContacts.setOwnerId(zcyOwner.getId());
            map.put("contacts", zcyOwnerContactsMapper.selectBySelective(zcyOwnerContacts));
        }
        map.put("owner", zcyOwner);
        return map;
    }

    @Override
    public Map getMaintain(Integer id) {
        Map map = new HashMap<>();
        ZcyMaintain zcyMaintain = new ZcyMaintain();
        zcyMaintain.setEstatesId(id);
        List<ZcyMaintain> list = zcyMaintainMapper.selectBySelective(zcyMaintain);
        if (list.size() > 0) {
            zcyMaintain = list.get(0);
            ZcyMaintainTax zcyMaintainTax = new ZcyMaintainTax();
            zcyMaintainTax.setMaintainId(zcyMaintain.getId());
            map.put("tax", zcyMaintainTaxMapper.selectBySelective(zcyMaintainTax));
            ZcyMaintainOther zcyMaintainOther = new ZcyMaintainOther();
            zcyMaintainOther.setMaintainId(zcyMaintain.getId());
            zcyMaintainOther.setType(0);//可上学校
            map.put("school", zcyMaintainOtherMapper.selectBySelective(zcyMaintainOther));
            zcyMaintainOther.setType(1);//看房时间;
            map.put("lookHouse", zcyMaintainOtherMapper.selectBySelective(zcyMaintainOther));
        }
        map.put("maintain", zcyMaintain);
        return map;
    }

    @Override
    public Map getKey(Integer id) {
        Map map = new HashMap<>();
        ZcyKey zcyKey = new ZcyKey();
        zcyKey.setEstatesId(id);
        List<ZcyKey> list = zcyKeyMapper.selectBySelective(zcyKey);
        if (list.size() > 0) {
            zcyKey = list.get(0);
        }
        map.put("key", zcyKey);
        return map;
    }

    @Override
    public Map getExpress(Integer id) {
        Map map = new HashMap<>();
        ZcyExpress zcyExpress = new ZcyExpress();
        zcyExpress.setEstatesId(id);
        List<ZcyExpress> list = zcyExpressMapper.selectBySelective(zcyExpress);
        if (list.size() > 0) {
            zcyExpress = list.get(0);
        }
        map.put("express", zcyExpress);
        return map;
    }

    @Override
    public Map addEstates(ZcyEstates zcyEstates, List<ZcyEstatesAddress> address, List<ZcyEstatesFacility> facilities) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyEstates.getId() == null) {
            result = zcyEstatesMapper.insertSelective(zcyEstates);
        } else {
            result = zcyEstatesMapper.updateByPrimaryKeySelective(zcyEstates);
            zcyEstatesAddressMapper.deleteByPrimaryKey(zcyEstates.getId());
            zcyEstatesFacilityMapper.deleteByPrimaryKey(zcyEstates.getId());
        }
        if (result > 0) {
            for (ZcyEstatesAddress addr : address) {
                addr.setEstatesId(zcyEstates.getId());
                zcyEstatesAddressMapper.insertSelective(addr);
            }
            for (ZcyEstatesFacility fac : facilities) {
                fac.setEstatesId(zcyEstates.getId());
                zcyEstatesFacilityMapper.insertSelective(fac);
            }
            map.put("result", "yes");
            map.put("estatesId", zcyEstates.getId());
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map addOwner(ZcyOwner zcyOwner, List<ZcyOwnerContacts> contactses) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyOwner.getEstatesId() == null) {
            map.put("result", "no_estatesId");
            return map;
        }
        if (zcyOwner.getId() == null) {
            result = zcyOwnerMapper.insertSelective(zcyOwner);
        } else {
            result = zcyOwnerMapper.updateByPrimaryKeySelective(zcyOwner);
            zcyOwnerContactsMapper.deleteByPrimaryKey(zcyOwner.getId());
        }
        if (result > 0) {
            for (ZcyOwnerContacts con : contactses) {
                con.setOwnerId(zcyOwner.getId());
                zcyOwnerContactsMapper.insertSelective(con);
            }
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map addMaintain(ZcyMaintain zcyMaintain, List<ZcyMaintainOther> others, List<ZcyMaintainTax> taxes) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyMaintain.getEstatesId() == null) {
            map.put("result", "no_estatesId");
            return map;
        }
        if (zcyMaintain.getId() == null) {
            result = zcyMaintainMapper.insertSelective(zcyMaintain);
        } else {
            result = zcyMaintainMapper.updateByPrimaryKeySelective(zcyMaintain);
            zcyMaintainOtherMapper.deleteByPrimaryKey(zcyMaintain.getId());
            zcyMaintainTaxMapper.deleteByPrimaryKey(zcyMaintain.getId());
        }
        if (result > 0) {
            for (ZcyMaintainOther oth : others) {
                oth.setMaintainId(zcyMaintain.getId());
                zcyMaintainOtherMapper.insertSelective(oth);
            }
            for (ZcyMaintainTax tax : taxes) {
                tax.setMaintainId(zcyMaintain.getId());
                zcyMaintainTaxMapper.insertSelective(tax);
            }
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map addKey(ZcyKey zcyKey) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyKey.getEstatesId() == null) {
            map.put("result", "no_estatesId");
            return map;
        }
        if (zcyKey.getId() == null) {
            result = zcyKeyMapper.insertSelective(zcyKey);
        } else {
            result = zcyKeyMapper.updateByPrimaryKeySelective(zcyKey);
        }
        if (result > 0) {
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map addExpress(ZcyExpress zcyExpress) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyExpress.getEstatesId() == null) {
            map.put("result", "no_estatesId");
            return map;
        }
        if (zcyExpress.getId() == null) {
            result = zcyExpressMapper.insertSelective(zcyExpress);
        } else {
            result = zcyExpressMapper.updateByPrimaryKeySelective(zcyExpress);
        }
        if (result > 0) {
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }
}
