package me.zhoubl.pay.common.service.biz;

import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;

/**
 * Created by zhoubl on 2017/2/13.
 */
public interface BaseBiz<Entity extends Serializable> extends IService<Entity> {
}
