package me.zhoubl.pay.sysmanage.service.optimizer;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhoubl on 2017/2/15.
 */
public class KryoSerializationOptimizer implements SerializationOptimizer {

    @Override
    public Collection<Class> getSerializableClasses() {
        List<Class> classes = new LinkedList<Class>();
        return classes;
    }
}
