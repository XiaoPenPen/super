package com.example.demo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.demo.user.group.ceshi;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.GroupSequence;
import java.io.Serializable;

@Data
@TableName("user")
@GroupSequence({Insert.class, ceshi.class, User.class})
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(groups = {ceshi.class}, message = "name不能为空!!!")
    private String name;
    // 版本号，用于实现乐观锁,数据库中也增加相应的version字段
    @Version // 这个注解是关键
    private Integer version;
}
