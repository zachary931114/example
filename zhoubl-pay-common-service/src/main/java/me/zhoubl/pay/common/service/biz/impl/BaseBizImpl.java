package me.zhoubl.pay.common.service.biz.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.Serializable;

/**
 * Created by zhoubl on 2017/2/13.
 */
public class BaseBizImpl<Dao extends BaseMapper<Entity>, Entity extends Serializable> extends ServiceImpl<Dao, Entity> {
}
