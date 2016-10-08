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
        Integer a =new Integer(111);
        Integer b =111;

        System.out.println(a==111);
    }

    //@Autowired
    //FollowUpMessageService service;

    //@Autowired
    //public FollowUpMessageMapper messageMapper;
    @Test
    public void p(){
        Integer a =111;
        Integer b =111;

        System.out.println(a==b);
    }


}
