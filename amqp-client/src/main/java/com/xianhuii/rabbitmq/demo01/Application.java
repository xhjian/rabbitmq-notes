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
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("return message: " + new String(body));
            }
        });
        Map<String, Object> headers = new HashMap<>();
        headers.put("color", "blue");
        headers.put("size", "big");
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().headers(headers).build();
        channel.basicPublish("amq.headers", "", true, properties, "hello".getBytes());
    }
}
