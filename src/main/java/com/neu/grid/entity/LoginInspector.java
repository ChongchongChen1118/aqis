package com.neu.grid.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (login_inspector)实体类
 *
 * @author kancy
 * @since 2023-07-05 20:40:05
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginInspector implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * inId
     */
    private Integer inId;
    /**
     * inAccount
     */
    private String inAccount;
    /**
     * inPass
     */
    private String inPass;
    /**
     * 账户注册时间
     */
    private Date inDate;

}