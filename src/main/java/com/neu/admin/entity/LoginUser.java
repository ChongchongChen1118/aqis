package com.neu.admin.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * usId
     */
    @TableId
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
     * 封禁状态
     */
    private Integer usOut;
    /**
     * 用户创建时间
     */
    private Date usDate;

}