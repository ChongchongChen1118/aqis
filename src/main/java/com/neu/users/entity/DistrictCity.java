package com.neu.users.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (district_city)实体类
 *
 * @author kancy
 * @since 2023-06-29 22:30:56
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictCity implements Serializable {
    private static final long serialVersionUID = 1L;

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

}