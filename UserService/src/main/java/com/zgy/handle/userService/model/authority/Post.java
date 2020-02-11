package com.zgy.handle.userService.model.authority;

import com.zgy.handle.userService.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "system_post")
@Data
@Slf4j
public class Post extends BaseModel {
    private String code;
    private String name;
}
