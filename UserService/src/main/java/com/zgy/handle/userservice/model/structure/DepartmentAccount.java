package com.zgy.handle.userservice.model.structure;

import com.zgy.handle.userservice.model.user.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author a4423
 */
@Entity
@Table(name = "system_department_account_test")
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Audited
public class DepartmentAccount implements Serializable {
    @EmbeddedId
    private DepartmentAccountPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("departId")
    @JoinColumn(name = "departId",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    @JoinColumn(name = "accountId",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    private Account account;

    // 部门人员级别
    @Enumerated(EnumType.STRING)
    private DepartPersonalType personalType;

    private int sortOrder;
}
