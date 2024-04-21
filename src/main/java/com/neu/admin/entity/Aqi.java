package com.neu.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Aqi implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 空气污染指数
     */
    @TableId
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