package com.example.demo.job;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 定时任务在线管理
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {

    List<QuartzJob> findByJobClassName(@Param("jobClassName") String jobClassName);

}