package com.neu.admin.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@Accessors(chain = true)
public class IdentUserDTO{
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Integer useId;
    /**
     * 用户头像
     */
    private String usePhoto;
    /**
     * 用户姓名
     */
    private String useName;
    /**
     * 用户性别
     */
    private String useSex;
    /**
     * 用户年龄
     */
    private Integer useAge;
    /**
     * 用户电话
     */
    private String usePhone;
    /**
     * 用户住址
     */
    private String useAddress;
    /**
     * 用户邮箱
     */
    private String useEmail;
    /**
     * 用户贡献数量
     */
    private Integer useContribution;
    /**
     * 用户个人介绍
     */
    private String useIntro;
    /**
     * 外键，对应账号密码表
     */
    private Integer useCid;

}