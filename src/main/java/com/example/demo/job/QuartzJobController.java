package com.example.demo.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Api(tags = "QuartzJobController",description = "定时任务接口")
@Validated
@RestController
@RequestMapping("/sys/quartzJob")
public class QuartzJobController {
    @Autowired
    private IQuartzJobService quartzJobService;

    @ApiOperation("定时任务分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer", paramType = "query"),
    })
    @GetMapping(value = "/pageInfo")
    public Result queryPageList(QuartzJob quartzJob, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest request) {
        Page<QuartzJob> page = new Page<>(pageNo, pageSize);
        //构造查询条件
        IPage<QuartzJob> pageList = quartzJobService.page(page, new QueryWrapper<QuartzJob>());
        return Result.init(pageList);
    }

    @ApiOperation("新增定时任务")
    @PostMapping
    public Result add(QuartzJob quartzJob, BindingResult result) {
        boolean b = quartzJobService.saveAndScheduleJob(quartzJob);
        if (b){
            return Result.init("成功");
        }
        return Result.error("失败");

    }

    @ApiOperation("修改定时任务")
    @PutMapping
    public Result eidt(QuartzJob quartzJob, BindingResult result) {
        try {
            quartzJobService.editAndScheduleJob(quartzJob);
        } catch (SchedulerException e) {
            log.error(e.getMessage(),e);
            return Result.error("该定时任务类名已存在");
        }
        return Result.init("成功");
    }

    @ApiOperation("删除定时任务")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        QuartzJob quartzJob = quartzJobService.getById(id);
        quartzJobService.deleteAndStopJob(quartzJob);
        return Result.init("成功");

    }


    @ApiOperation(value = "暂停定时任务")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "query")
    @PatchMapping(value = "/pause")
    public Result pauseJob(@RequestParam String id) {
        QuartzJob job = quartzJobService.getById(id);
        quartzJobService.pause(job);
        return Result.init("成功");
    }

    @ApiOperation(value = "恢复定时任务")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "query")
    @PatchMapping(value = "/resume")
    public Result resumeJob(@RequestParam String id) {
        QuartzJob job = quartzJobService.getById(id);
        quartzJobService.resumeJob(job);
        return Result.init("成功");
    }

    @ApiOperation("通过id获取任务明细")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") String id) {
        QuartzJob quartzJob = quartzJobService.getById(id);
        return Result.init(quartzJob);
    }

    @ApiOperation("删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @GetMapping("/removeTrigger/{id}")
    public Result deleteTrigger(@PathVariable("id") String id) throws Exception {
        quartzJobService.deleteTrigger(id);
        return Result.init();
    }


    @ApiOperation("立即执行")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "query")
    @PatchMapping("/execute")
    public Result execute(@RequestParam String id) {
        QuartzJob quartzJob = quartzJobService.getById(id);
        try {
            quartzJobService.execute(quartzJob);
        } catch (Exception e) {
            //e.printStackTrace();
            log.info("定时任务 立即执行失败>>"+e.getMessage());
            return Result.error("失败");
        }
        return Result.init("成功");

    }
}