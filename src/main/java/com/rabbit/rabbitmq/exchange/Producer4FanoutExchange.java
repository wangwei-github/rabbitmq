package com.rabbit.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer4FanoutExchange {
    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("wangwei");
        connectionFactory.setPassword("123456");
        connectionFactory.setHost("192.168.11.129");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);

        //建立连接
        Connection connection = connectionFactory.newConnection();

        //通过连接建立通道
        Channel channel = connection.createChannel();

        //声明
        String exchangeName = "test.fanout.exchang";
        String routingKey = "kkk";
        String str = "这是fanout测试！！！";
        channel.basicPublish(exchangeName, routingKey, null, str.getBytes());
        channel.basicPublish(exchangeName, routingKey, null, str.getBytes());
        channel.basicPublish(exchangeName, routingKey, null, str.getBytes());
        channel.close();
        connection.close();
    }
}
