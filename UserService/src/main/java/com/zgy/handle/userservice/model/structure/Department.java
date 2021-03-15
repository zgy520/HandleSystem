package com.zgy.handle.userservice.model.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.common.model.BaseModel;
import com.zgy.handle.userservice.model.user.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "system_department")
@Data
@Slf4j
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@SQLDelete(sql = "update system_department set isDeleted = true where id = ?")
@Where(clause = BaseModel.SOFT_DELETED_CLAUSE)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Department extends BaseModel implements Serializable {
    @EqualsAndHashCode.Include
    private String code;
    private String name;
    // 部门类型
    private String type;
    @Column(name = "sortOrder", columnDefinition = "int default 0")
    // 排列顺序
    private int sortOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    @JsonIgnore
    private Department parent;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "system_department_account_test",
            joinColumns = @JoinColumn(name = "departId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "none")),
            inverseJoinColumns = @JoinColumn(name = "accountId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "none")))
    private Set<Account> accountSet;

    // 所属企业
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterpriseId", referencedColumnName = "id")
    @JsonIgnore
    private Enterprise enterprise;

    public void addAccount(Account account) {
        this.accountSet.add(account);
        account.getDepartmentSet().add(this);
    }

    public void removeAccount(Account account){
        this.accountSet.remove(account);
        account.getDepartmentSet().remove(this);
    }

    public void removeAllAccount(){
        Iterator<Account> iterator = this.accountSet.iterator();
        while (iterator.hasNext()){
            Account account = iterator.next();
            account.getDepartmentSet().remove(this);
            iterator.remove();
        }
    }

}
