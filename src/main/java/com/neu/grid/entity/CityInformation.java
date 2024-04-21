package com.neu.grid.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (city_information)实体类
 *
 * @author kancy
 * @since 2023-07-08 00:25:59
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityInformation implements Serializable {
    private static final long serialVersionUID = 1L;

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

}