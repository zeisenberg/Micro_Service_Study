package com.szkingdom.order.server.message;

import com.rabbitmq.tools.json.JSONUtil;
import com.szkingdom.order.server.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author zhaizhengwei
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

//    @StreamListener(value = StreamClient.INPUT)
//    public void process(Object message) {
//        log.info("StreamReceiver: {}", message);
//    }

    /**
     * 接收OrderDOT对象 消息
     * @param orderDTO
     */
    @StreamListener(value = StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2)
    public String process(OrderDTO orderDTO) {
        log.info("StreamReceiver: {}", orderDTO);
        return "received";
    }


    @StreamListener(value = StreamClient.INPUT2)
    public void process2(String message) {
        log.info("StreamReceiver: {}", message);
    }
}
