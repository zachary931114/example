package me.zhoubl.pay.common.service.redis;

import net.jcip.annotations.ThreadSafe;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhoubl on 2017/2/11.
 */
@ThreadSafe
public class MyBatisRedisCache implements Cache {

	private static final Logger logger = LoggerFactory.getLogger(MyBatisRedisCache.class);

	private static RedisTemplate<String, Object> redisTemplate;

	private final String id;

	private static int expire = 0;

	private final String keyPrefix = "mybatis_redis_cache:";

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public MyBatisRedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("MyBatisRedisCache id不能为空");
		}
		logger.debug("MyBatisRedisCache:id=" + id);
		this.id = id;
	}

	public String getKey(Object key) {
		return this.keyPrefix + key;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	@Override
	public void putObject(Object key, Object value) {
		if (key != null && value != null) {
			redisTemplate.opsForValue().set(getKey(key), value, expire, TimeUnit.MILLISECONDS);
		}
	}

	@Override
	public Object getObject(Object key) {
		Object result = null;
		if (key != null) {
			result = redisTemplate.opsForValue().get(getKey(key));
		}
		return result;
	}

	@Override
	public Object removeObject(Object key) {
		Object result = null;
		if (key != null) {
			redisTemplate.delete(getKey(key));
		}
		return result;
	}

	@Override
	public int getSize() {
		Long size = (Long) redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
		return size.intValue();
	}

	@Override
	public void clear() {
		// Jedis Cluster 不支持
	}

	public static RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public static void setRedisTemplate(RedisTemplate redisTemplate) {
		MyBatisRedisCache.redisTemplate = redisTemplate;
	}

	public static int getExpire() {
		return expire;
	}

	public static void setExpire(int expire) {
		MyBatisRedisCache.expire = expire;
	}

}
