package com.rabbit.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer4DirectExchange {
    public static void main(String[] args) throws Exception {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("wangwei");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("192.168.11.129");
        connectionFactory.setPort(5672);

        //2.建立连接
        Connection connection = connectionFactory.newConnection();

        //3.通过连接建立通道
        Channel channel = connection.createChannel();

        //4.声明
        String exchangeName = "direct.test.exchange";
        String routingKey = "direct.test";
        //5.发布消息
        for (int i = 0; i < 6; i++) {
            String str = "这是direct路由！！！";
            channel.basicPublish(exchangeName, routingKey, null, str.getBytes());
        }
        channel.close();
        connection.close();
    }
}




