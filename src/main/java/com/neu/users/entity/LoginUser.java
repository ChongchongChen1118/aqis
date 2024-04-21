package com.neu.users.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (login_user)实体类
 *
 * @author kancy
 * @since 2023-06-28 23:06:20
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * usId
     */
    private Integer usId;
    /**
     * usAccount
     */
    private String usAccount;
    /**
     * usPass
     */
    private String usPass;
    /**
     * usOut
     */
    private Integer usOut;
    /**
     * 用户创建时间
     */
    private Date usDate;

}