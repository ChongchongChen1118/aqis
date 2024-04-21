package com.neu.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DistrictProvince implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 省的主键
     */
    @TableId
    private Integer prId;
    /**
     * 省的名称
     */
    private String prName;

}