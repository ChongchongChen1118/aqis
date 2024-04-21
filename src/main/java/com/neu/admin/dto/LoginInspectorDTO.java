package com.neu.admin.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginInspectorDTO {
    private static final long serialVersionUID = 1L;

    /**
     * inId
     */
    @TableId
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

    private IdentInspectorDTO identInspectorList;

}