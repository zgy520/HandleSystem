package com.zgy.handle.userservice.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userservice.model.BaseModel;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.structure.DepartmentAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author a4423
 */
@Data
@Slf4j
@Entity
@Audited
@Table(name = "system_account")
@EqualsAndHashCode(callSuper = true, of = {"id"})
@SQLDelete(sql = "update system_account set isDeleted = true where id = ?")
@Where(clause = BaseModel.SOFT_DELETED_CLAUSE)

public class Account extends BaseModel implements Serializable {
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

    @ManyToMany
    @JoinTable(name = "system_role_personal",
            joinColumns = {@JoinColumn(name = "accountId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    @ToString.Exclude
    @Singular("roleSet")
    @JsonIgnore
    private Set<Role> roleSet = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "system_post_personal",
            joinColumns = {@JoinColumn(name = "accountId")},
            inverseJoinColumns = {@JoinColumn(name = "postId")})
    @JsonIgnore
    @ToString.Exclude
    @Singular("postSet")
    private Set<Post> postSet = new HashSet<>();

    @ManyToMany(mappedBy = "accountSet")
    @ToString.Exclude
    private Set<Department> departmentSet= new HashSet<>();

    /*@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<DepartmentAccount> departmentAccounts = new HashSet<>();*/

    public void addRole(Role role) {
        this.roleSet.add(role);
        //role.getAccountSet().add(this);
    }

    public void removeRole(Role role) {
        this.roleSet.remove(role);
        //role.getAccountSet().remove(this);
    }

}
