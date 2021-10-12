package com.xianhuii.rabbitmq.demo01;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer01_2 {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare("myExchange", BuiltinExchangeType.DIRECT);
            channel.queueDeclare("myQueue", true, false, false, null);
            channel.queueBind("myQueue", "myExchange", "myRoutingKey");
            GetResponse response = channel.basicGet("myQueue", true);
            if (response != null) {
                System.out.println(new String(response.getBody()));
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
