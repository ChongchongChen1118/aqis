package com.neu.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityInformationDTO {
    /**
     * infId
     */
    private Integer infId;
    /**
     * infSo
     */
    private Double infSo;
    /**
     * infCo
     */
    private Double infCo;
    /**
     * infPm
     */
    private Double infPm;
    /**
     * infAqi
     */
    private Integer infAqi;
    /**
     * infCid
     */
    private Integer infCid;
    /**
     * infDate
     */
    private Date infDate;
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
    /**
     * 空气污染指数
     */
    private Integer aqId;
    /**
     * 空气污染名称
     */
    private String aqName;
    /**
     * 空气质量对应颜色
     */
    private String aqColor;
}
