package com.zgy.handle.userservice.model.authority.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.common.model.BaseModel;
import com.zgy.handle.userservice.model.user.Account;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author a4423
 */
@Entity
@Table(name = "system_role")
@Data
@Slf4j
//@Audited
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"id"})
@SQLDelete(sql = "update system_role set isDeleted = true where id = ?")
@Where(clause = BaseModel.SOFT_DELETED_CLAUSE)
public class Role extends BaseModel implements Serializable {
    private String code;
    private String name;
    @ManyToMany
    @JoinTable(name = "system_role_personal",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "accountId")})
    @JsonIgnore
    @Singular("accountSet")
    @ToString.Exclude
    private Set<Account> accountSet = new HashSet<>();

    public void addAccount(Account account) {
        this.accountSet.add(account);
        //account.getRoleSet().add(this);
    }

    public void addAccountList(List<Account> accountList) {
        this.accountSet.addAll(accountList);
    }

    public void removeAccount(Account account) {
        this.accountSet.remove(account);
        //account.getRoleSet().remove(this);
    }

}
