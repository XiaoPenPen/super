package com.example.demo.callable;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*IntStream.range(1, 100).parallel().forEach(i-> {
            System.out.println(Thread.currentThread().getName() + "----------" + i);
        });
        System.out.println("执行完毕");*/
        //test();
        executionTask("xcp");

    }

    private static final ConcurrentMap<Object, Future<Map<String, Integer>>> taskCache = new ConcurrentHashMap<>();

    private static void executionTask(final String taskName)throws ExecutionException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);
        List list=new ArrayList();
        Callable<String> task = () -> taskName;
        for(int j=0;j<5;j++){
            Future<Map<String, Integer>> future = taskCache.get(taskName); // 1.1,2.1
            Callable<Map<String, Integer>> c1 = new CallableThread(j,latch);
            FutureTask<Map<String, Integer>> futureTask = new FutureTask<>(c1);
            future = taskCache.putIfAbsent(taskName, futureTask); // 1.3
            future = futureTask;
            list.add(future);
            futureTask.run(); // 1.4执行任务
            System.out.println(future.get().get("data"));
        }
        try {
            System.out.println(1111111111);
        } catch (CancellationException e) {
            e.printStackTrace();
        }
    }

    public static void test(){
        final CountDownLatch latch = new CountDownLatch(5);
        ExecutorService pool= Executors.newFixedThreadPool(5);
        List list=new ArrayList();
        try {
            for(int j=0;j<5;j++){
                Callable<Map<String, Integer>> c1 = new CallableThread(j,latch);
                Future<Map<String,Integer>> f1=pool.submit(c1);
                list.add(f1);
            }
            latch.await();
            System.out.println("1111111111111111");

            for(int i=0;i<list.size();i++){
                Future<Map<String,Integer>> f1=(Future<Map<String, Integer>>) list.get(i);
                System.out.println((Integer)f1.get().get("data"));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


}
