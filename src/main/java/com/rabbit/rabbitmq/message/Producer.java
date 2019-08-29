package com.rabbit.rabbitmq.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;


public class Producer {
    public static void main(String[] args) throws Exception {
        //1.创建连接工厂并配置
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.11.129");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("wangwei");
        factory.setPassword("123456");
        //2.创建连接
        Connection connection = factory.newConnection();
        //3.通过连接创建通道
        Channel channel = connection.createChannel();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("msg", "你好啊1");
        headers.put("msg2", "你好啊2");
        headers.put("msg3", "你好啊3");
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder().contentEncoding("utf-8").expiration("10000").deliveryMode(2)
                .headers(headers)
                .build();

        for (int i = 0; i < 6; i++) {
            String str = "Hello RabbitMQ你好!!!";
            channel.basicPublish("", "wangwei", basicProperties, str.getBytes());
        }

        //4.关闭连接
        channel.close();
        connection.close();
    }


}
