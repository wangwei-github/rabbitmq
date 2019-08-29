package com.rabbit.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer4FanoutExchange {

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
        String exchangeType = "fanout";
        String routingKey = "iiii";
        String queueName = "test.fanout.queue";
        channel.exchangeDeclare(exchangeName, exchangeType, false, false, null);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        //创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //建立队列与消费者的关系
        channel.basicConsume(queueName, true, queueingConsumer);

        //接收消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.err.println("消费者： " + msg);
        }
    }
}
