package com.neu.users.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (idea_workout)实体类
 *
 * @author kancy
 * @since 2023-06-29 19:19:04
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaWorkout implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 是否评价了，否0，是1
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

}