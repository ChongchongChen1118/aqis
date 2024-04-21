package com.neu.admin.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginUserDTO {
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

    private IdentUserDTO identUserDTO;
}
