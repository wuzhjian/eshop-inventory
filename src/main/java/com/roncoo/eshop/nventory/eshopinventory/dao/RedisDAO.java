package com.roncoo.eshop.nventory.eshopinventory.dao;

import org.springframework.stereotype.Repository;


public interface RedisDAO {
    void set(String key,String value);

    String get(String key);

    void delete(String key);
}
