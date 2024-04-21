package com.neu.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DistrictCity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 城市主键
     */
    @TableId
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