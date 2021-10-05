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
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                // 业务处理
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                // 业务处理
            }
        });
        Map<String, Object> headers = new HashMap<>();
        headers.put("color", "blue");
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().headers(headers).expiration("10000").build();
        channel.basicPublish("amq.headers", "", true, properties, "hello".getBytes());
        channel.queueDeclare("testQueue", true, false, false, null);
        channel.basicConsume("myQueue", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
        GetResponse message = channel.basicGet("myQueue", true);
        System.out.println(new String(message.getBody()));
        GetResponse myQueue = channel.basicGet("myQueue", false);
        System.out.println(new String(message.getBody()));
        channel.basicAck(myQueue.getEnvelope().getDeliveryTag(), false);
        channel.exchangeDeclare("myExchange", BuiltinExchangeType.DIRECT);
        channel.queueDeclare("myQueue", true, false, false, null);
        channel.exchangeBind("myQueue", "myExchange", "myRoutingKey");
        channel.basicPublish("myExchange", "myRoutingKey", null, "hello".getBytes());
    }
}
