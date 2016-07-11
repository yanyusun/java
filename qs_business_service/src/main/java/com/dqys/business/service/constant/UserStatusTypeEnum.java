package com.dqys.business.service.constant;

import com.dqys.core.base.BaseSelectonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @apiDefine UserStatusTypeEnum
 * @apiSuccessExample {json} UserStatusTypeEnum-Success-Response:
 * {
 * 0:"未激活"
 * }
 * Created by Yvan on 16/6/30.
 * <p/>
 * 成员用户状态枚举
 */
public enum UserStatusTypeEnum {

    UNACTIVE(0, "未激活"),
    NORMAL(1, "正常"),
    NOLOGIN(2, "禁止登陆"),
    STOP(3, "停用");

    private Integer value;
    private String name;

    UserStatusTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static UserStatusTypeEnum getUserStatusTypeEnum(Integer value) {
        if (value != null) {
            for (UserStatusTypeEnum userStatusTypeEnum : UserStatusTypeEnum.values()) {
                if (userStatusTypeEnum.equals(value)) {
                    return userStatusTypeEnum;
                }
            }
        }
        return null;
    }

    public static List<BaseSelectonDTO> listUserStatusTypeEnum() {
        List<BaseSelectonDTO> selectonDTOList = new ArrayList<>();
        for (UserStatusTypeEnum userStatusTypeEnum : UserStatusTypeEnum.values()) {
            BaseSelectonDTO baseSelectonDTO = new BaseSelectonDTO();
            baseSelectonDTO.setKey(userStatusTypeEnum.getValue().toString());
            baseSelectonDTO.setValue(userStatusTypeEnum.getName());
            selectonDTOList.add(baseSelectonDTO);
        }
        return selectonDTOList;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
