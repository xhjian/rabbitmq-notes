package com.xianhuii.rabbitmq.demo01;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Application {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
//        channel.addReturnListener(new ReturnListener() {
//            @Override
//            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println("handleReturn");
//                System.out.println(replyText);
//                System.out.println(exchange);
//                System.out.println(routingKey);
//                System.out.println(new String(body));
//            }
//        });
        AMQP.Confirm.SelectOk selectOk = channel.confirmSelect();
        channel.basicPublish("1", "myqueue", false, null, "hello".getBytes());
        try {
            boolean flag = channel.waitForConfirms();
            System.out.println(flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        channel.basicConsume("myqueue", false, new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println(new String(body));
//                channel.basicAck(envelope.getDeliveryTag(), false);
//            }
//        });
    }
}
