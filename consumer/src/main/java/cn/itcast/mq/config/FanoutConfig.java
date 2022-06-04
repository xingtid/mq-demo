package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    // 创建交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    // 创建队列
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutQueue1");
    }

    // 创建队列
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanoutQueue2");
    }

    // 绑定队列到交换机
    @Bean
    public Binding bindingExchangeFanout1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    // 绑定队列到交换机
    @Bean
    public Binding bindingExchangeFanout2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("test.queue");
    }
}
