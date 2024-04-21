package com.neu.admin.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.neu.admin.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class IdeaDTO{
    private static final long serialVersionUID = 1L;

    /**
     * 表的主键
     */
    @TableId
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

    private DistrictCity districtCity;

    private DistrictProvince districtProvince;

    private IdentInspector identInspector;

    private Aqi aqi;

    private IdentUser identUser;
    private IdeaWorkout ideaWorkout;
    private Aqi workedAqi ;

}