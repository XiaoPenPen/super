package com.example.demo.job;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @Description: 定时任务在线管理
 */
@Slf4j
@Service
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJob> implements IQuartzJobService {
    @Autowired
    private Scheduler scheduler;

    /**
     * 立即执行的任务分组
     */
    private static final String JOB_TEST_GROUP = "test_group";

    @Override
    public List<QuartzJob> findByJobClassName(String jobClassName) {
        return super.list(Wrappers.<QuartzJob>lambdaQuery().eq(QuartzJob::getJobClassName,jobClassName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAndScheduleJob(QuartzJob quartzJob) {
        if (new Integer(0).equals(quartzJob.getStatus())) {
            // 定时器添加
            this.schedulerAdd(quartzJob.getJobClassName().trim(), quartzJob.getCronExpression().trim(), quartzJob.getParameter(), JOB_TEST_GROUP);
        }
        return this.save(quartzJob);
    }


    @Override
    public boolean resumeJob(QuartzJob quartzJob) {
        schedulerDelete(quartzJob.getJobClassName().trim(), JOB_TEST_GROUP + quartzJob.getParameter());
        schedulerAdd(quartzJob.getJobClassName().trim(), quartzJob.getCronExpression().trim(), quartzJob.getParameter(), JOB_TEST_GROUP);
        quartzJob.setStatus(0);//正常状态
        return this.updateById(quartzJob);
    }


    @Override
    public boolean editAndScheduleJob(QuartzJob quartzJob) throws SchedulerException {
        if (new Integer(0).equals(quartzJob.getStatus())) {
            schedulerDelete(quartzJob.getJobClassName().trim(), JOB_TEST_GROUP + quartzJob.getParameter());
            schedulerAdd(quartzJob.getJobClassName().trim(), quartzJob.getCronExpression().trim(), quartzJob.getParameter(), JOB_TEST_GROUP);
        }else{
            scheduler.pauseJob(JobKey.jobKey(quartzJob.getJobClassName().trim()));
        }
        return this.updateById(quartzJob);
    }

    @Override
    public boolean deleteAndStopJob(QuartzJob job) {
        schedulerDelete(job.getJobClassName().trim(), JOB_TEST_GROUP + job.getParameter());
        boolean ok = this.removeById(job.getId());
        return ok;
    }

    @Override
    public void execute(QuartzJob quartzJob) throws Exception {
        String jobName = quartzJob.getJobClassName().trim();
        Date startDate = new Date();
//		String ymd = DateUtils.date2Str(startDate,DateUtils.yyyymmddhhmmss.get());
        String ymd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        String identity =  jobName + ymd;
        //3秒后执行 只执行一次
        startDate.setTime(startDate.getTime()+3000L);
        // 定义一个Trigger
        SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger()
                .withIdentity(identity, JOB_TEST_GROUP)
                .startAt(startDate)
                .build();
        // 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobName).getClass()).withIdentity(identity).usingJobData("parameter", quartzJob.getParameter()).build();
        // 将trigger和 jobDetail 加入这个调度
        scheduler.scheduleJob(jobDetail, trigger);
        // 启动scheduler
        scheduler.start();
    }

    @Override
    public void pause(QuartzJob quartzJob){
        schedulerDelete(quartzJob.getJobClassName().trim(), JOB_TEST_GROUP + quartzJob.getParameter());
        quartzJob.setStatus(-1);//停用状态
        this.updateById(quartzJob);
    }

    @Override
    public void deleteTrigger(String id) throws Exception{
        scheduler.pauseTrigger(TriggerKey.triggerKey(id));
        scheduler.unscheduleJob(TriggerKey.triggerKey(id));
        scheduler.deleteJob(JobKey.jobKey(id));
    }

    /**
     * 添加定时任务
     *
     * @param jobClassName
     * @param cronExpression
     * @param parameter
     */
    private void schedulerAdd(String jobClassName, String cronExpression, String parameter, String group) {
        try {
            // 启动调度器
            scheduler.start();

            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, group+parameter).usingJobData("parameter", parameter).build();

            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, group+parameter).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
//			throw new Exception("创建定时任务失败", e);

        } catch (RuntimeException e) {
//			throw new Exception(e.getMessage(), e);
        }catch (Exception e) {
//			throw new Exception("后台找不到该类名：" + jobClassName, e);
        }
    }

    /**
     * 删除定时任务
     *
     * @param jobClassName
     */
    private void schedulerDelete(String jobClassName, String group) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, group));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, group));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, group));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
//			throw new Exception("删除定时任务失败");
        }
    }

    private static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }

}