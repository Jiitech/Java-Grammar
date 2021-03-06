package com.wukj.thread.sync;

import java.lang.Thread;
import java.util.ArrayList;

public class ThreadSyncMethod{

    /**
     * 1）当一个线程正在访问一个对象的synchronized方法，那么其他线程不能访问该对象的其他synchronized方法。这个原因很简单，因为一个对象只有一把锁，当一个线程获取了该对象的锁之后，其他线程无法获取该对象的锁，所以无法访问该对象的其他synchronized方法。
     * 2）当一个线程正在访问一个对象的synchronized方法，那么其他线程能访问该对象的非synchronized方法。这个原因很简单，访问非synchronized方法不需要获得该对象的锁，假如一个方法没用synchronized关键字修饰，说明它不会使用到临界资源，那么其他线程是可以访问这个方法的。
     * 3）如果一个线程A需要访问对象object1的synchronized方法fun1，另外一个线程B需要访问对象object2的synchronized方法fun1，即使object1和object2是同一类型），也不会产生线程安全问题，因为他们访问的是不同的对象，所以不存在互斥问题。
     * 
     * @param args
     */
    
    public static void main(String[] args) {
        
        final InsertData insertData = new InsertData();
         
        new Thread() {
            public void run() {
                insertData.insert(Thread.currentThread());
            };
        }.start();
         
        new Thread() {
            public void run() {
                insertData.insert(Thread.currentThread());
            };
        }.start();

    }





}

class InsertData {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    /**
     * 当多线程访问synchronized修饰的方法的时候，其中一个线程获取了当前对象的锁，其他线程只有等这个线程执行完成，其他线程才能执行这个方法。
     * @param thread
     */ 
    public synchronized void insert(Thread thread){
        for(int i=0;i<5;i++){
            System.out.println(thread.getName()+"在插入数据"+i);
            arrayList.add(i);
        }
    }

}