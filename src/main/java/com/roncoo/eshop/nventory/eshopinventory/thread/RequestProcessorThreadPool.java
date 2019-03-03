package com.roncoo.eshop.nventory.eshopinventory.thread;

import com.roncoo.eshop.nventory.eshopinventory.request.RequestQueue;
import com.roncoo.eshop.nventory.eshopinventory.request.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池： 单例
 * 请求处理线程池
 * @author 44644
 */
public class RequestProcessorThreadPool {
    /**
     * 实际项目，线程池配置，每个线程监控内存队列大小多少，都可以做到外部配置文件
     */
    /**
     * 线程池
     */
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    /**
     * 内存队列
     */
    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();

    public RequestProcessorThreadPool(){

        RequestQueue requestQueue = RequestQueue.getInstance();

        for (int i = 0; i < 10; i++) {
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(100);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }

    private static class Singleton{
        private static RequestProcessorThreadPool instance;
        static {
            instance = new RequestProcessorThreadPool();
        }
        public static RequestProcessorThreadPool getInstance(){
            return instance;
        }
    }

    /**
     * jvm的机制去保证多线程并发安全
     * 内部类初始化，一定只会发生一次，不管多少线程并发去初始化
     * @return
     */
    public static RequestProcessorThreadPool getInstance(){
        return Singleton.getInstance();
    }

    /**
     * 初始化方法
     */
    public static void init(){
        getInstance();
    }

}
