package com.zgy.handle.userservice.model.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.common.model.BaseModel;
import com.zgy.handle.userservice.model.user.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "system_post")
@Data
@Slf4j
//@Audited
@EqualsAndHashCode(callSuper = true, of = {"id"})
public class Post extends BaseModel implements Serializable {
    private String code;
    private String name;
    @ManyToMany
    @JoinTable(name = "system_post_personal",
            joinColumns = {@JoinColumn(name = "postId")},
            inverseJoinColumns = {@JoinColumn(name = "accountId")})
    @JsonIgnore
    @Singular("accountSet")
    @ToString.Exclude
    private Set<Account> accountSet = new HashSet<>();
}
