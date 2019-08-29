package com.rabbit.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer4TopicExchange {
    public static void main(String[] args) throws Exception{
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
        String exchangeName = "test.topic.exchange";
        String routingKey = "topic.test";
        String routingKey2 = "topic.test.www";
        String routingKey3 = "topic.add";
        //5.发布消息
            String str = "这是direct路由！！！";
            channel.basicPublish(exchangeName, routingKey, null, str.getBytes());
            channel.basicPublish(exchangeName, routingKey2, null, str.getBytes());
            channel.basicPublish(exchangeName, routingKey3, null, str.getBytes());
        channel.close();
        connection.close();
    }

}
