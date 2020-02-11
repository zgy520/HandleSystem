package com.zgy.handle.userService.model.authority;

import com.zgy.handle.userService.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "system_role")
@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseModel {
    private String code;
    private String name;
}
