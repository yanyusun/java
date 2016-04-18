package com.dqys.core.utils;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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
