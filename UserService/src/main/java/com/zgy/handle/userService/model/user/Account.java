package com.zgy.handle.userService.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Slf4j
@Entity(name = "system_account")
public class Account extends BaseModel {
    private String name;
    private String loginName;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String salt;
    @Email
    private String email;
    @JsonIgnore
    private boolean isExpired = false;
    @JsonIgnore
    private boolean isLocked = false;
    @JsonIgnore
    private boolean isEnabled = true;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date expiredDate;
}
