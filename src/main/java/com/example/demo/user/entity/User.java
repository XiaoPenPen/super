package com.example.demo.user.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.demo.user.group.ceshi;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.GroupSequence;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
@GroupSequence({Insert.class, ceshi.class, User.class})
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(groups = {ceshi.class}, message = "name不能为空!!!")
    private String name;
    private String indexName;
    // 版本号，用于实现乐观锁,数据库中也增加相应的version字段
    @Version // 这个注解是关键
    private Integer version;

    @JsonProperty("BankSettlementDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM/ddHHmmss", locale = "zh", timezone = "GMT+8")
    private Date bankSettlementDate;

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setBankSettlementDate(DateUtil.date());
        System.out.println(mapper.writeValueAsString(user));

        JSONConfig jsonConfig = JSONConfig.create();
        jsonConfig.setDateFormat("yyyy年MM/ddHHmmss");
        System.out.println(JSONUtil.toJsonStr(JSONUtil.parse(user, jsonConfig)));

    }
}
