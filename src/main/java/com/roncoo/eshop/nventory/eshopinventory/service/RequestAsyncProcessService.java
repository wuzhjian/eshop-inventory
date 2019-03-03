package com.roncoo.eshop.nventory.eshopinventory.service;

import com.roncoo.eshop.nventory.eshopinventory.request.Request;

/**
 * 请求异步执行service
 * @author 44644
 */
public interface RequestAsyncProcessService {


    void process(Request request);

}
