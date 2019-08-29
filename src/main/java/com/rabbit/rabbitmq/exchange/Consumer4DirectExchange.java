package com.rabbit.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer4DirectExchange {

    public static void main(String[] args) throws Exception{
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("wangwei");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("192.168.11.129");
        connectionFactory.setPort(5672);
        connectionFactory.setAutomaticRecoveryEnabled(true);//异常断开重连开启
        connectionFactory.setNetworkRecoveryInterval(3000);//3S
        //2.创建连接
        Connection connection = connectionFactory.newConnection();

        //3.建立通道
        Channel channel = connection.createChannel();
        //4.声明
        String exchangeName ="direct.test.exchange";
        String exchangeType = "direct";
        String queueName = "direct.test.queue";
        String routingKey = "direct.test";
        channel.exchangeDeclare(exchangeName, exchangeType,true,false,null);
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName, exchangeName, routingKey);

        //5.创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //6.设置通道
        channel.basicConsume(queueName, true, queueingConsumer);
        //7.循环接收消息
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.err.println(msg);
        }


    }
}
