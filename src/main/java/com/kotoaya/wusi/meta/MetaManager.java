package com.kotoaya.wusi.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

import java.io.IOException;

/**
 * 读取元信息
 */
public class MetaManager {
    private static volatile Meta meta;//保证多个线程的内存可见
    //获取meta的唯一实例
    public static Meta getMeta(){
        //双检锁校验
        if (meta==null){
            synchronized (MetaManager.class){
                if (meta==null){
                    meta=initMeta();
                }
            }
        }
        return meta;
    }

    //meta初始化
    private static Meta initMeta(){
        String metajson = ResourceUtil.readUtf8Str("meta/meta.json");
        return JSONUtil.toBean(metajson, Meta.class);
    }
}
