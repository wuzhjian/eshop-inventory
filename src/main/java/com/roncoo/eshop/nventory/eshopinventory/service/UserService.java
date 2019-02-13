package com.roncoo.eshop.nventory.eshopinventory.service;

import com.roncoo.eshop.nventory.eshopinventory.model.User;

public interface UserService {
    /**
     * 查询用户信息
     * @return
     */
    public User findUserInfo();

    /**
     * 查询redis中缓存信息
     * @return
     */
    public User getCacheUserInfo();
}
