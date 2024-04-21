package com.neu.grid.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (ident_inspector)实体类
 *
 * @author kancy
 * @since 2023-07-06 14:49:39
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class IdentInspectorDTO implements Serializable {
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

    /**
     * 城市主键
     */
    private Integer ciId;
    /**
     * 城市名称
     */
    private String ciName;
    /**
     * 外键，城市对应的省
     */
    private Integer ciCid;
    /**
     * 省的主键
     */
    private Integer prId;
    /**
     * 省的名称
     */
    private String prName;

}