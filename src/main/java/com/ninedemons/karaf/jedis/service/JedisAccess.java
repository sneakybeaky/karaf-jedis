package com.ninedemons.karaf.jedis.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Jon Barber at AKQA
 */
public class JedisAccess {
    private JedisPool jedisPool;

    public void setPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public String ping() {
        if (this.jedisPool == null) {
            throw new RuntimeException("No Jedis pool selected");
        }

        Jedis jedis = this.jedisPool.getResource();

        try {
            return jedis.ping();

        } finally {
            this.jedisPool.returnResource(jedis);
        }

    }

    public String info() {
        if (this.jedisPool == null) {
            throw new RuntimeException("No Jedis pool selected");
        }

        Jedis jedis = this.jedisPool.getResource();

        try {
            return jedis.info();

        } finally {
            this.jedisPool.returnResource(jedis);
        }

    }
}
