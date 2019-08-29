package com.rabbit.rabbitmq.message;

import com.rabbitmq.client.*;

public class Consumer {
    public static void main(String[] args) throws Exception{
        //1.创建连接工厂并配置
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.11.129");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setPassword("123456");
        factory.setUsername("wangwei");
        //2.创建连接
        Connection connection = factory.newConnection();
        //3.通过连接创建通道
        Channel channel = connection.createChannel();

        //4.声明一队列
        channel.queueDeclare("wangwei", true, false, false, null);

        //5.创建消费者

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //6.设置通道
        channel.basicConsume("wangwei", true, queueingConsumer);

        //7.获取消息
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.err.println("消费者："+msg);
            Envelope envelope = delivery.getEnvelope();
            boolean redeliver = envelope.isRedeliver();
            long deliveryTag = envelope.getDeliveryTag();
            String exchange = envelope.getExchange();
            String routingKey = envelope.getRoutingKey();
            System.err.println("redeliver:"+redeliver+"  deliveryTag:"+deliveryTag+"  exchange:"+exchange+"  routingKey:"+routingKey);
        }
    }
}
