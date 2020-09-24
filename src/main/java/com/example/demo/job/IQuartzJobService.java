package com.example.demo.job;

import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @Description: 定时任务在线管理
 */
public interface IQuartzJobService extends IService<QuartzJob> {

    /**
     * 通过名称获取任务列表
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);

    /**
     * 保存&启动定时任务
     * @param quartzJob
     * @return
     */
    boolean saveAndScheduleJob(QuartzJob quartzJob);

    /**
     * 编辑&启停定时任务
     * @throws SchedulerException
     */
    boolean editAndScheduleJob(QuartzJob quartzJob) throws SchedulerException;


    /**
     * 删除&停止删除定时任务
     */
    boolean deleteAndStopJob(QuartzJob quartzJob);

    /**
     * 恢复定时任务
     * @param quartzJob
     * @throws SchedulerException
     */
    boolean resumeJob(QuartzJob quartzJob);

    /**
     * 执行定时任务
     * @param quartzJob
     */
    void execute(QuartzJob quartzJob) throws Exception;

    /**
     * 暂停任务
     * @param quartzJob
     * @throws SchedulerException
     */
    void pause(QuartzJob quartzJob);

    void deleteTrigger(String id) throws SchedulerException, Exception;
}