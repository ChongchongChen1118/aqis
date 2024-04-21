package com.neu.users.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (ident_user)实体类
 *
 * @author kancy
 * @since 2023-07-01 22:17:33
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class IdentUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
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