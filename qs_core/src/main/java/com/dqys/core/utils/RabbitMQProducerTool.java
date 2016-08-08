package com.dqys.core.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author by pan on 16-4-14.
 */
@Component
public class RabbitMQProducerTool implements ApplicationContextAware {

    private static RabbitTemplate rabbitTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);

        /*rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                rabbitRetryTemplate.execute(retryContext -> {
                    retryContext.setAttribute("message", message);
                    rabbitTemplate.convertAndSend(exchange, routingKey, message);
                    return null;
                }, retryContext -> {
                    Message message1 = (Message) retryContext.getAttribute("message");
                    message1.getBody();
                    Throwable t = retryContext.getLastThrowable();
                    t.printStackTrace();
                    return null;
                });
            }
        });*/
        /*rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                message.getMessageProperties().isRedelivered();
            }
        });
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(!ack) {
                    rabbitRetryTemplate.execute(new RetryCallback<Object, RuntimeException>() {
                        @Override
                        public Object doWithRetry(RetryContext retryContext) throws RuntimeException {
                            rabbitTemplate.send((Message) retryContext.getAttribute("message"));
                            return null;
                        }
                    }, new RecoveryCallback<Object>() {
                        @Override
                        public Object recover(RetryContext retryContext) throws Exception {
                            retryContext.setAttribute("message", retryContext);
                            return null;
                        }
                    });
                }
            }
        });*/
    }

    public static void addToMailSendQueue(String to, String msg) {
        rabbitTemplate.convertAndSend("mailExchange", "mail_online_route", new String[] {to, msg});
    }


    /*public static void sendTest() throws IOException {
        Message m = MessageBuilder.withBody("aaaatest".getBytes()).build();
        //rabbitTemplate.send(m);
        rabbitTemplate.send("myExchange", "foo.bar", m);
        rabbitTemplate.send("myExchange2", "test.bar", m);
        System.out.println("m");
        *//*Message message = MessageBuilder.withBody("foo".getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("123")
                .setHeader("bar", "baz")
                .build();

        rabbitTemplate.send("myExchange", "foo.bar", message);
        System.out.println("a");*//*

        *//*rabbitTemplate.convertAndSend("bbbbbbbbbbbbbb");
        System.out.println("b");*//*
    }

    public static void reciveTest() {
        Message message = rabbitTemplate.receive("mail_send_queue");
        String[] objs = (String[]) SerializationUtils.deserialize(message.getBody());
        System.out.println(objs);
    }*/
}
