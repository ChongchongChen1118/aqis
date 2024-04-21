
package com.neu.admin.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class IdentInspectorDTO{
    private static final long serialVersionUID = 1L;

    /**
     * 检测员信息表主键
     */
    @TableId
    private Integer insId;
    /**
     * 检测员头像
     */
    private String insPhoto;
    /**
     * 检测员姓名
     */
    private String insName;
    /**
     * 检测员电话
     */
    private String insPhone;
    /**
     * 检测员邮箱
     */
    private String insEmail;
    /**
     * 检测员身份证号
     */
    private String insCard;
    /**
     * 检测员住址
     */
    private String insAddress;
    /**
     * 检测员的满意数
     */
    private Integer insContribution;
    /**
     * 检测员的差评数
     */
    private Integer insNegative;
    /**
     * 外键，对应某个检测员
     */
    private Integer insCid;

}