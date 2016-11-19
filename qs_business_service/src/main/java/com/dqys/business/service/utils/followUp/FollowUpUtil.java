package com.dqys.business.service.utils.followUp;

import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;

/**
 * Created by yan on 16-8-15.
 */
public class FollowUpUtil {
    public static FollowUpMessage toFollowUpMessage(FollowUpMessageDTO followUpMessageDTO){
        FollowUpMessage followUpMessage= new FollowUpMessage();
        followUpMessage.setObjectId(followUpMessageDTO.getObjectId());
        followUpMessage.setContent(followUpMessageDTO.getContent());
        followUpMessage.setObjectType(followUpMessageDTO.getObjectType());
        followUpMessage.setLiquidateStage(followUpMessageDTO.getLiquidateStage());
        if(null!=followUpMessageDTO.getSecondLiquidateStage()){
            followUpMessage.setSecondLiquidateStage(followUpMessageDTO.getSecondLiquidateStage());
        }
        //// TODO: 16-11-18 为了保证前端没有id传入也能显示展示修改成如下
        //原来
//        if(null!=followUpMessageDTO.getSecondObjectId()){
//            followUpMessage.setSecondObjectType(followUpMessageDTO.getSecondObjectType());
//            followUpMessage.setSecondObjectId(followUpMessageDTO.getSecondObjectId());
//        }
        //改后
        if(null!=followUpMessageDTO.getSecondObjectId()){
            followUpMessage.setSecondObjectId(followUpMessageDTO.getSecondObjectId());
        }
        if(null!=followUpMessageDTO.getSecondObjectId()){
            followUpMessage.setSecondObjectType(followUpMessageDTO.getSecondObjectType());
        }

        return followUpMessage;
    }
}
