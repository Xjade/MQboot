package com.study.jade;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;

import java.util.List;

public class TestMainClass {


    @Test
    public void test1() throws Exception{
        //设置组名,并实例化
        DefaultMQProducer producer = new
                DefaultMQProducer("producerGroup1");
        // 名称服务,分号分割
//        producer.setNamesrvAddr("192.168.229.5:9876;192.168.229.6:9876");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            //消息实例
            Message msg = new Message("topic2" /* Topic */,
                    "Tagb" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }

    @Test
    public void test2(){
        //实例化消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup1");
//        consumer.setNamesrvAddr("192.168.229.5:9876;192.168.229.6:9876");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        try {
            //订阅topic1下的所有tag的消息
            consumer.subscribe("topic2", "*");
            //注册回调
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            System.out.printf("Consumer Started.%n");
            while (true) {
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
