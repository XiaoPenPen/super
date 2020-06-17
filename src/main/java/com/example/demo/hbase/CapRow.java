package com.example.demo.hbase;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author dushixiang
 * @date 2019-03-16 16:10
 */
@Data
@Accessors(chain = true)
public class CapRow<T> {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp;
    private T result;
}
