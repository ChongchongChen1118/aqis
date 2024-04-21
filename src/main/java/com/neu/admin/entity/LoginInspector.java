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