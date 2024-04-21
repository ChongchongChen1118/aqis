package com.neu.admin.dto;

import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class IdeaWork1DTO {
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
    /**
     * 主键
     */
    private Integer woId;
    /**
     * 检测员实地监测后所拍照片
     */
    private String woPhoto;
    /**
     * 检测员是滴检测后的so2值
     */
    private Double woSo;
    /**
     * co2值
     */
    private Double woCo;
    /**
     * pm2.5值
     */
    private Double woPm;
    /**
     * 外键，对应idea表主键
     */
    private Integer woCid;
    /**
     * 检测员检测后的aqi值
     */
    private Integer woAqi;
    /**
     * 是否评价了，是1，否0
     */
    private Integer woEvaluate;
    /**
     * 外键，对应检测员
     */
    private Integer woInsCid;
    /**
     * 意见解决时间
     */
    private Date woDate;
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
