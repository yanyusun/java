package com.dqys.business.service.utils.sysNotice;

import com.dqys.business.orm.pojo.sysNotice.SysNotice;
import com.dqys.business.service.dto.sysNotice.SysNoticeDTO;
import com.dqys.core.model.UserSession;

/**
 * Created by yan on 16-9-7.
 */
public class SysNoticeUtil {
    public static SysNotice toSysNotice(SysNoticeDTO sysNoticeDTO){
        SysNotice sysNotice = new SysNotice();
        sysNotice.setContent(sysNoticeDTO.getContent());
        sysNotice.setPicname(sysNoticeDTO.getPicname());
        sysNotice.setTitle(sysNoticeDTO.getTitle());
        sysNotice.setType(sysNoticeDTO.getType());
        sysNotice.setUserId(UserSession.getCurrent().getUserId());
        return sysNotice;
    }
}
