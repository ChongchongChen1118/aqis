package com.neu.grid.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (idea_workout)实体类
 *
 * @author kancy
 * @since 2023-07-06 09:56:03
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
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
    private String woSo;
    /**
     * co2值
     */
    private String woCo;
    /**
     * pm2.5值
     */
    private String woPm;
    /**
     * 外键，对应idea表主键
     */
    private String woCid;
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