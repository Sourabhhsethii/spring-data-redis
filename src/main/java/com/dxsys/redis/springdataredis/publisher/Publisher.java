package com.dxsys.redis.springdataredis.publisher;

import com.dxsys.redis.springdataredis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Publisher {

    @Autowired
    private RedisTemplate pubSubRedisTemplate ;

    @Autowired
    private ChannelTopic channelTopic;

    @PostMapping("/publish")
    public String publish(@RequestBody Product product){
        pubSubRedisTemplate.convertAndSend(channelTopic.getTopic(),product.toString());
        return "Event Published!";
    }

}
