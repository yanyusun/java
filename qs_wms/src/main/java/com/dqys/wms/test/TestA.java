package com.dqys.wms.test;

/**
 * Created by yan on 16-8-15.
 */
import com.dqys.business.orm.mapper.followUp.FollowUpMessageMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestA extends BaseTest{
    public static void main(String[] args) {

    }

    //@Autowired
    //FollowUpMessageService service;

    @Autowired
    public FollowUpMessageMapper messageMapper;
    @Test
    public void p(){
        FollowUpMessage message = new FollowUpMessage();
        message.setTeamId(22);
        message.setUserId(22);
        message.setObjectId(2121);
        message.setObjectType(22);
        message.setLiquidateStage(12);
        messageMapper.insert(message);
    }
}
