package com.neu.admin.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class IdeaWorkout implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
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

}