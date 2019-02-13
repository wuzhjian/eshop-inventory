package com.roncoo.eshop.nventory.eshopinventory.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 请求内存队列
 */
public class RequestQueue {
    // 内存队列
    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();

    private static class Singleton{
        private static RequestQueue instance;
        static {
            instance = new RequestQueue();
        }
        public static RequestQueue getInstance(){
            return instance;
        }
    }
    public static RequestQueue getInstance(){
        return RequestQueue.Singleton.getInstance();
    }
    /**
     * 添加一个内存队列
     * @param queue
     */
    public void addQueue(ArrayBlockingQueue<Request> queue){
        this.queues.add(queue);
    }


    /**
     * 获取队列的数量
     * @return
     */
    public int queueSize(){
        return queues.size();
    }

    /**
     * 获取内存队列
     * @param index
     * @return
     */
    public ArrayBlockingQueue<Request> getQueue(int index){
        return queues.get(index);
    }
}
