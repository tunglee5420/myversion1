package com.just.myproject.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redisUtil工具类
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;



    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                ValueOperations<Serializable, Object> operations=redisTemplate.opsForValue();
                operations.set(key,value,time, TimeUnit.MINUTES);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            ValueOperations<Serializable, Object> operations=redisTemplate.opsForValue();
            operations.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
    * 普通缓存获取
    * @param key 键
    * @return 值
    */
    public Object get(String key) {
        ValueOperations<Serializable, Object> operations=redisTemplate.opsForValue();
        return key == null ? null : operations.get(key);
    }
    public boolean deleteKey(String key){
        return key==null||redisTemplate.delete(key)==true?true:false;
    }

    /**
     * 取出key 的值并且删除key
     * @param key
     * @return
     */
    public Object getAndDelete(String key){
        ValueOperations<Serializable, Object> operations=redisTemplate.opsForValue();
        if(key!=null&&operations.get(key)!=null){
            Object value=operations.get(key);
            deleteKey(key);
            return value;
        }
        return null;
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值对
     * @return true成功 false失败
     */
    public boolean setMap(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, String> getMap(String key) {
        if(key!=null){
            Map map=redisTemplate.opsForHash().entries(key);
            return map;
        }
        return null;
    }
}
