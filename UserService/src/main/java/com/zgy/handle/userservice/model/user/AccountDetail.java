package com.zgy.handle.userservice.model.user;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * @author a4423
 */
@Entity
@Table(name = "system_account_detail")
@Data
@Slf4j
public class AccountDetail implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_short")
    @GenericGenerator(name = "uuid_short", strategy = "com.zgy.handle.userService.util.hibernate.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Account account;

    /**
     * 人员编码
     */
    private String code;
    /**
     * 姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * email地址
     */
    @Email
    private String email;
    /**
     * 唯一编号
     */
    private String identity;
    /**
     * 性别
     */
    private int sex;
    /**
     * 地址
     */
    private String address;
}
