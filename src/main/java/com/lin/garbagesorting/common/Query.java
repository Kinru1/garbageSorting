package com.lin.garbagesorting.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Query {
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gpDay;
}
