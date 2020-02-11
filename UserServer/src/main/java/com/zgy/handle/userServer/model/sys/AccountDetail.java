package com.zgy.handle.userServer.model.sys;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "system_account_detail")
@Data
@Slf4j
public class AccountDetail {

    @Id
    @GeneratedValue(generator = "uuid_short")
    @GenericGenerator(name = "uuid_short",strategy = "com.zgy.handle.userServer.model.UUIDGenerator")
    @Column(name = "id",nullable = false)
    private Long id;
    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Account account;
    private String code; // 人员编码
    private String nickName; // 昵称
    private String identity; // 唯一编号
    private int sex; // 性别
    private String address; // 地址
}
