package com.zgy.handle.userService.model.structure;

import com.zgy.handle.userService.model.user.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "system_enterprise_account")
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnterpriseAccount {
    @EmbeddedId
    private EnterpriseAccountPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("enterpriseId")
    @JoinColumn(name = "enterpriseId",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Enterprise enterprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    @JoinColumn(name = "accountId",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Account account;

    @Enumerated(EnumType.STRING)
    private EnterprisePersonalType personalType; // 部门人员级别

    private int sortOrder;
}
