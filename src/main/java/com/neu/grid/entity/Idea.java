package com.neu.grid.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (idea)实体类
 *
 * @author kancy
 * @since 2023-07-05 21:29:44
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Idea implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表的主键
     */
    private Integer idId;
    /**
     * 用户提意见的照片
     */
    private String idPhoto;
    /**
     * 外键，用户所提意见对应城市的主键
     */
    private Integer idCity;
    /**
     * 外键，用户所提意见所对应的aqi
     */
    private Integer idAqi;
    /**
     * 外键，用户所提意见的用户id
     */
    private Integer idUser;
    /**
     * 外键，用户所提某条意见所对应的检测员
     */
    private Integer idInspector;
    /**
     * 用户所提意见的备注
     */
    private String idRemark;
    /**
     * 用户的意见是否解决，0表示未解决，1表示解决
     */
    private Integer idOut;
    /**
     * 意见创建时间
     */
    private Date idDate;
}