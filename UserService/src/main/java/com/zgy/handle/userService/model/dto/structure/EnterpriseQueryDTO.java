package com.zgy.handle.userService.model.dto.structure;

import com.zgy.handle.userService.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseQueryDTO extends BaseDTO {
    private String code;
    private String name;
    private String shortName;
    private String phonePerson;
    private String phoneNumber;
    private String phoneEmail;
    private String registerPerson;
    private String identity;
    private Date initorDate;
    private String address;
    private String note;
    private String parentId;
    private String industryId;
    private String industryName;
    private List<EnterpriseQueryDTO> children = new ArrayList<>();
}
