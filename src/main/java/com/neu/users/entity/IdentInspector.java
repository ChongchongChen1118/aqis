package com.neu.users.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (ident_inspector)实体类
 *
 * @author kancy
 * @since 2023-07-01 23:55:14
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class IdentInspector implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 检测员信息表主键
     */
    private Integer insId;
    /**
     * 检测员头像
     */
    private String insPhoto;
    /**
     * 检测员姓名
     */
    private String insName;
    /**
     * 检测员电话
     */
    private String insPhone;
    /**
     * 检测员邮箱
     */
    private String insEmail;
    /**
     * 检测员身份证号
     */
    private String insCard;
    /**
     * 检测员住址
     */
    private Integer insAddress;
    /**
     * 检测员的满意数
     */
    private Integer insContribution;
    /**
     * 检测员的差评数
     */
    private Integer insNegative;
    /**
     * 外键，对应某个检测员
     */
    private Integer insCid;

}