package com.neu.admin.dto;

import com.neu.admin.entity.DistrictCity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ProvinceCityDTO {
    private static final long serialVersionUID = 1L;

    private Integer prId;

    private String prName;

    private List<DistrictCity> districtCity;



}
