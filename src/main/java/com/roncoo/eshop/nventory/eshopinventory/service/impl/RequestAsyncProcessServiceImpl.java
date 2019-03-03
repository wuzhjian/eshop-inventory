package com.roncoo.eshop.nventory.eshopinventory.service.impl;

import com.roncoo.eshop.nventory.eshopinventory.request.ProductInventoryCacheRefreshRequest;
import com.roncoo.eshop.nventory.eshopinventory.request.ProductInventoryDBUpdateRequest;
import com.roncoo.eshop.nventory.eshopinventory.request.Request;
import com.roncoo.eshop.nventory.eshopinventory.request.RequestQueue;
import com.roncoo.eshop.nventory.eshopinventory.service.RequestAsyncProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 请求异步处理的service实现
 * @author 44644
 */
@Service("requestAsyncProcessService")
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {
    @Override
    public void process(Request request) {

        try {
            // 先做读请求去重
            RequestQueue requestQueue = RequestQueue.getInstance();
            Map<Integer, Boolean> flagMap = requestQueue.getFlagMap();

            if (request instanceof ProductInventoryDBUpdateRequest){
                // 如果是一个更新数据库的请求，那么就讲那个productId对应的标识设置为true
                flagMap.put(request.getProduceId(), true);
            } else if (request instanceof ProductInventoryCacheRefreshRequest){
                Boolean flag = flagMap.get(request.getProduceId());

                // 如果flag是null
                if (flag == null){
                    flagMap.put(request.getProduceId(), false);
                }

                // 如果是缓存刷新请求，那么就判断，如果标识不为空，而且是true，就说明
                // 之前有一个这个商品的数据更新请求
                if (flag != null && flag){
                    flagMap.put(request.getProduceId(), false);
                }

                // 如果是缓存刷新的请求，而且发现标识不为空，但是标识为false
                // 说明前面已经有一个数据库更新请求+一个缓存刷新请求了
                if (flag != null && !flag){
                    // 对于这种请求，直接过滤掉，不要放到后面的内存队列里面去了
                    return;
                }

            }
            // 做请求的路由，根据每个商品的id，路由到对应的内存队列中去
            ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProduceId());
            // 将请求放入对应的队列中，完成路由操作
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取路由到的内存队列
     * @param productId
     * @return
     */
    private ArrayBlockingQueue<Request> getRoutingQueue(Integer productId){
        RequestQueue requestQueue = RequestQueue.getInstance();

        // 先获取productId的hash值
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >> 16);
        // 对hash值取模，将hash值路由到指定内存队列中，比如内存队列大小8
        // 用内存队列的数量对hash值取模之后，结果一定在0-7之间
        // 所以任何一个商品id都会被固定路由到同样的一个内存队列中去
        int index = (requestQueue.queueSize() - 1) & hash;
        return requestQueue.getQueue(index);
    }
}
