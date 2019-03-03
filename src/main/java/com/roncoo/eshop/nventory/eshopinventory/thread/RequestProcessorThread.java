package com.roncoo.eshop.nventory.eshopinventory.thread;

import com.roncoo.eshop.nventory.eshopinventory.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @author 44644
 */
public class RequestProcessorThread implements Callable<Boolean> {
    /**
     * 自己监控的内存队列
     */
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            while (true) {
                // ArrayBlockingQueue
                // Blocking就是说明，如果队列满了或者是空的，那么在执行操作的时候阻塞住
                Request request = queue.take();
                // 执行这个操作
                request.process();
            }
        } catch (Exception e){

        }
        return false;
    }
}

