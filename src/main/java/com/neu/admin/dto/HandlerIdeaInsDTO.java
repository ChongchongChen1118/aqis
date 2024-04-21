package com.neu.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class HandlerIdeaInsDTO {
    private Integer idId;
    private Integer cityId;
    private Integer idUser;


}
