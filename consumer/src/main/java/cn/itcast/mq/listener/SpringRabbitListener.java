package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SpringRabbitListener {
//    @RabbitListener(queues = "simple.queue")
//    public void process(String msg) {
//        System.out.println("simple.queue接收到消息：" + msg);
//    }

    @RabbitListener(queues = "simple.queue")
    public void processWork1(String msg) throws InterruptedException {
        System.out.println("1---simple.queue接收到消息：" + msg + "----" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void processWork2(String msg) throws InterruptedException {
        System.out.println("2---simple.queue接收到消息：" + msg + "----" + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanoutQueue1")
    public void fanoutQueue1(String msg) {
        System.out.println("1---fanoutQueue1接收到消息：" + msg + "----" + LocalTime.now());
    }

    @RabbitListener(queues = "fanoutQueue2")
    public void fanoutQueue2(String msg) {
        System.out.println("2---fanoutQueue2接收到消息：" + msg + "----" + LocalTime.now());
    }
}
