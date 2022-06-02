package cn.itcast.mq.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {
        rabbitTemplate.convertAndSend("simple.queue", "hello world");
    }

    @Test
    public void testSendWorkQueue() throws InterruptedException {
        for (int i = 0; i < 50; i++) {

            rabbitTemplate.convertAndSend("simple.queue", "hello world" + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendFanoutExchange() {
        // 交换机名称
        String exchange = "fanoutExchange";
        // 消息
        String msg = "hello world";
        // 发送消息到交换机
        rabbitTemplate.convertAndSend(exchange, "", msg);
    }
}
