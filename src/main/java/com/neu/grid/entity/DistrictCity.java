package com.neu.grid.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (district_city)实体类
 *
 * @author kancy
 * @since 2023-07-05 23:41:34
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
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