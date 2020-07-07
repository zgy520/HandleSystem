package com.zgy.handle.userService.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.BaseModel;
import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Slf4j
@Entity(name = "system_account")
@EqualsAndHashCode(callSuper = true, of = {"id"})
@SQLDelete(sql = "update system_account set isDeleted = true where id = ?")
@Where(clause = BaseModel.SOFT_DELETED_CLAUSE)
public class Account extends BaseModel {
    /*@Size(min = 10, max = 20, message = "姓名必须在10到20个字符之间")*/
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_role_personal",
            joinColumns = {@JoinColumn(name = "accountId")},
            inverseJoinColumns = { @JoinColumn(name = "roleId")})
    @ToString.Exclude
    @Singular("roleSet")
    @JsonIgnore
    private Set<Role> roleSet = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_post_personal",
            joinColumns = {@JoinColumn(name = "accountId")},
            inverseJoinColumns = { @JoinColumn(name = "postId")})
    @JsonIgnore
    @ToString.Exclude
    @Singular("postSet")
    private Set<Post> postSet = new HashSet<>();

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private Set<DepartmentAccount> departmentAccounts = new HashSet<>();

    public void addRole(Role role){
        this.roleSet.add(role);
        //role.getAccountSet().add(this);
    }

    public void removeRole(Role role){
        this.roleSet.remove(role);
        //role.getAccountSet().remove(this);
    }
}
