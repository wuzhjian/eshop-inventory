package com.roncoo.eshop.nventory.eshopinventory.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roncoo.eshop.nventory.eshopinventory.dao.RedisDAO;
import com.roncoo.eshop.nventory.eshopinventory.mapper.UserMapper;
import com.roncoo.eshop.nventory.eshopinventory.model.User;
import com.roncoo.eshop.nventory.eshopinventory.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisDAO redisDAO;

    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User getCacheUserInfo() {
        redisDAO.set("cached_user_lisi","{\"name\": \"lisi\", \"age\":28}");
        String userJSON = redisDAO.get("cached_user_lisi");
        JSONObject userJSONObject = JSON.parseObject(userJSON);

        User user = new User();
        user.setName(userJSONObject.getString("name"));
        user.setAge(userJSONObject.getInteger("age"));
        return user;
    }
}
