package com.study.ybdk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName("course")
@NoArgsConstructor
public class Course implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String name;
    @JsonProperty("kId")
    private Integer kId;
}
