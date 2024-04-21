package com.neu.users.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (district_province)实体类
 *
 * @author kancy
 * @since 2023-06-29 22:20:54
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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