package com.xianhuii.rabbitmq.demo01;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publish01 {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare("myExchange", BuiltinExchangeType.DIRECT);
            channel.queueDeclare("myQueue", true, false, false, null);
            channel.queueBind("myQueue", "myExchange", "myRoutingKey");
            channel.basicPublish("myExchange", "myRoutingKey", null, "message".getBytes());
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
