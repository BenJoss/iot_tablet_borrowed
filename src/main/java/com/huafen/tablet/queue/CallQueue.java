package com.huafen.tablet.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class CallQueue {

	static final int QUEUE_MAX_SIZE   = 10000;

	static ArrayBlockingQueue<Object> blockingQueue=new ArrayBlockingQueue<Object>(QUEUE_MAX_SIZE);
	
	 /**
                * 私有的默认构造子，保证外界无法直接实例化
     */
    private CallQueue(){};
    /**
               * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
                   * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder{
        /**
                             * 静态初始化器，由JVM来保证线程安全
         */
        private  static CallQueue queue = new CallQueue();
    }
    
    //单例队列
    public static CallQueue getCallQueue(){
        return SingletonHolder.queue;
    }
    
   //生产入队
    public  void  produce(Object object) throws InterruptedException {
        blockingQueue.put(object);
    }
    //消费出队
    public  Object consume() throws InterruptedException {
        return blockingQueue.take();
    }
    // 获取队列大小
    public int size() {
        return blockingQueue.size();
    }
}
