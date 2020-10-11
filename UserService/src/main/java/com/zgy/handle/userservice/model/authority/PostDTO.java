package com.zgy.handle.userservice.model.authority;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO extends BaseDTO {
    private String name;
    private String code;
    private String note;
    private List<String> userList = new ArrayList<>();
}