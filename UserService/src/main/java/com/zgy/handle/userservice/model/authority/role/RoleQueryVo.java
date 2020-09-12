package com.zgy.handle.userservice.model.authority.role;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.Data;

@Data
public class RoleQueryVo extends BaseDTO {
    private String code;
    private String name;

}
