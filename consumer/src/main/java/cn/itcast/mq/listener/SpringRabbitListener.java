package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

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

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "directExchange"),
            key = {"red", "blue"}))
    public void directQueue1(String msg) {
        System.out.println("1---directQueue1接收到消息：" + msg + "----" + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "directExchange"),
            key = {"red", "yellow"}))
    public void directQueue2(String msg) {
        System.out.println("2---directQueue2接收到消息：" + msg + "----" + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "topicExchange", type = ExchangeTypes.TOPIC),
            key = "china.#"))
    public void topicQueue1(String msg) {
        System.out.println("1---topicExchange1接收到消息：" + msg + "----" + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "topicExchange", type = ExchangeTypes.TOPIC),
            key = "#.news"))
    public void topicQueue2(String msg) {
        System.out.println("2---topicExchange2接收到消息：" + msg + "----" + LocalTime.now());
    }

    @RabbitListener(queues = "test.queue")
    public void objectQueue(Map<String, String> msg) {
        System.out.println("object.queue接收到消息：" + msg + "----" + LocalTime.now());
    }
}
