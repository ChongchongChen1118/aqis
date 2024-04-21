package com.neu.grid.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (district_province)实体类
 *
 * @author kancy
 * @since 2023-07-06 15:46:29
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DistrictProvince implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 省的主键
     */
    private Integer prId;
    /**
     * 省的名称
     */
    private String prName;

}