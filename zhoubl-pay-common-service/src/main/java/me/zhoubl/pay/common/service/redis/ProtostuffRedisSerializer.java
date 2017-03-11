package me.zhoubl.pay.common.service.redis;

import me.zhoubl.pay.common.utils.serialization.ProtostuffPack;
import me.zhoubl.pay.common.utils.serialization.ProtostuffSerializer;
import net.jcip.annotations.ThreadSafe;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by zhoubl on 2017/2/11.
 */
@ThreadSafe
public class ProtostuffRedisSerializer implements RedisSerializer<Object> {

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o != null){
            ProtostuffPack protostuffPack = new ProtostuffPack();
            protostuffPack.setObject(o);
            return ProtostuffSerializer.serialize(protostuffPack);
        }else{
            return null;
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes != null) {
            return ProtostuffSerializer.deserialize(bytes, ProtostuffPack.class).getObject();
        } else {
            return null;
        }

    }
}
