package com.zgy.handle.userService.model.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.BaseModel;
import com.zgy.handle.userService.model.user.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "system_post")
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true, of = {"id"})
public class Post extends BaseModel {
    private String code;
    private String name;
    @ManyToMany
    @JoinTable(name = "system_post_personal",
            joinColumns = {@JoinColumn(name = "postId")},
            inverseJoinColumns = { @JoinColumn(name = "accountId")})
    @JsonIgnore
    @Singular("accountSet")
    @ToString.Exclude
    private Set<Account> accountSet = new HashSet<>();
}
