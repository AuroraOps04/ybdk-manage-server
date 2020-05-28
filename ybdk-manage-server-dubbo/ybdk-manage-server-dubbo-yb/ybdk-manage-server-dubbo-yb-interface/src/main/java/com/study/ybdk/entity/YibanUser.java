package com.study.ybdk.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * yiban_user
 * @author 
 */
@Data
@Builder
public class YibanUser implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String realName;

    private String sex;

    private String studentId;

    private String headUrl;

    private static final long serialVersionUID = 1L;
}