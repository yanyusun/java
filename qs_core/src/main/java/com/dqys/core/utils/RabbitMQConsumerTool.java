package com.dqys.core.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

/**
 * @author by pan on 16-4-15.
 */
public class RabbitMQConsumerTool implements ApplicationListener<ListenerContainerConsumerFailedEvent> {

    @Autowired
    private static RabbitTemplate rabbitTemplate;


    @Override
    public void onApplicationEvent(ListenerContainerConsumerFailedEvent listenerContainerConsumerFailedEvent) {

    }


    public void listen(String data) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa" + data);
    }

}
