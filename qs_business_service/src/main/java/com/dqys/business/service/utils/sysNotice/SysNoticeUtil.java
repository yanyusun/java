package com.dqys.business.service.utils.sysNotice;

import com.dqys.business.orm.pojo.sysNotice.SysNotice;
import com.dqys.business.service.dto.sysNotice.SysNoticeDTO;
import com.dqys.business.service.exception.bean.ParamConvertException;
import com.dqys.core.model.UserSession;

/**
 * Created by yan on 16-9-7.
 */
public class SysNoticeUtil {
    public static SysNotice toSysNotice(SysNoticeDTO sysNoticeDTO) throws ParamConvertException {
        if (sysNoticeDTO.getContent() == null || sysNoticeDTO.getTitle() == null) {
            throw new ParamConvertException("参数错误:content-->" + sysNoticeDTO.getContent() + ";title-->" + sysNoticeDTO.getTitle(), ParamConvertException.PARAM_ISNOULL_ERROR);
        } else {
            SysNotice sysNotice = new SysNotice();
            sysNotice.setContent(sysNoticeDTO.getContent());
            sysNotice.setPicname(sysNoticeDTO.getPicname());
            sysNotice.setTitle(sysNoticeDTO.getTitle());
            sysNotice.setType(sysNoticeDTO.getType());
            sysNotice.setUserId(UserSession.getCurrent().getUserId());
            sysNotice.setIntroduce(sysNoticeDTO.getIntroduce());
            return sysNotice;
        }

    }
}
