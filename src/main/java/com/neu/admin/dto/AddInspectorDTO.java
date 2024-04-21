package com.neu.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AddInspectorDTO {

    private String account;

    private String password;

    private String photo;

    private String name;

    private String phone;

    private String email;

    private String idCard;

    private String address;
}
