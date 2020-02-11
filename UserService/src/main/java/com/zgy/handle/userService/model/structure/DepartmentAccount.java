package com.zgy.handle.userService.model.structure;

import com.zgy.handle.userService.model.user.Account;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "system_department_account")
@Data
@Slf4j
public class DepartmentAccount {
    @EmbeddedId
    private DepartmentAccountPK id;

    @ManyToOne
    @MapsId("departId")
    @JoinColumn(name = "departId")
    private Department department;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "accountId")
    private Account account;

    @Enumerated(EnumType.STRING)
    private DepartPersonalType personalType; // 部门人员级别

    private int sortOrder;
}
