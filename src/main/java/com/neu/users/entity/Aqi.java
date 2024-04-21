package com.neu.users.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (aqi)实体类
 *
 * @author kancy
 * @since 2023-06-29 22:50:42
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aqi implements Serializable {
    private static final long serialVersionUID = 1L;

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