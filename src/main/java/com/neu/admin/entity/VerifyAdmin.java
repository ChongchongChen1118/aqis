package com.neu.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class VerifyAdmin implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * adId
     */
    @TableId
    private Integer adId;
    /**
     * adAccount
     */
    private String adAccount;
    /**
     * adPass
     */
    private String adPass;
    /**
     * 管理员头像
     */
    private String adPhoto;

}