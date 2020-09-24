package com.example.demo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

/**
 * @author xuchunpeng 2020/9/24
 */
public class JobTest implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("test:" + jobExecutionContext.getMergedJobDataMap().get("parameter"));
        System.out.println(LocalTime.now());
    }
}
