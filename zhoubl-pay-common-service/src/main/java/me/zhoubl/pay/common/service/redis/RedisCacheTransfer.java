package me.zhoubl.pay.common.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by zhoubl on 2017/2/14.
 */
@Component
public class RedisCacheTransfer {

	@Autowired
	public void setRedisTemplate(RedisTemplate redisTemplate, @Value("${mybatis.redis.cache.expire}") int expire) {
		MyBatisRedisCache.setRedisTemplate(redisTemplate);
		MyBatisRedisCache.setExpire(expire);
	}

}
