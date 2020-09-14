package com.example.demo.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * FutrueTask demo
 */
public class FutureTaskForMultiCompute {

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        FutureTaskForMultiCompute inst = new FutureTaskForMultiCompute();
        // 创建任务集合
        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
        ExecutorService execut = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            // 传入Callable对象创建FutureTask对象
            // inst.new ComputeTask(i, " "+i); 不太明白什么意思
            FutureTask<Integer> task = new FutureTask<Integer>(
                    inst.new ComputeTask(i, Thread.currentThread().getName() + i));
            // 把多个future放在集合中
            taskList.add(task);
            // 提交给线程池执行任务，也可以通过exec.invokeAll(taskList)一次性提交所有任务;
            execut.submit(task);
            System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！");
        }
        Integer result = 0;
        // 从future中取出值来
        for (FutureTask<Integer> future : taskList) {
            result = result + future.get();
        }
        // 打印求出来的值
        System.out.println("result=" + result);
    }

    public class ComputeTask implements Callable<Integer> {
        private Integer result = 0;
        private String taskName = "";

        ComputeTask(Integer result, String taskName) {
            this.result = result;
            this.taskName = taskName;
            System.out.println("生成子线程计算任务: " + taskName);
        }

        public String getTaskName() {
            return this.taskName;
        }

        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 10; i++) {
                result += i;

            }
            Thread.sleep(10000);
            System.out.println("子线程计算任务: " + taskName + " 执行完成!");
            return result;
        }

    }
}