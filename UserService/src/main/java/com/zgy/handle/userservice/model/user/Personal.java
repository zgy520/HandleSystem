package com.zgy.handle.userservice.model.user;

import com.zgy.handle.common.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * @author a4423
 */
@Entity(name = "system_personal")
@Data
@Slf4j
public class Personal extends BaseModel implements Serializable {

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
     * 邮箱
     */
    private String phoneNumber;
    /**
     * 人员状态（在校、离校）
     */
    private String personalStatus;
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
    /**
     * 关联账号的ID
     */
    private Long accountId;
    /**
     *  卡号
     */
    private String cardNum;
    /**+
     * 用户卡的状态
     */
    @Enumerated(EnumType.STRING)
    private CardStatusEnum cardStatusEnum;
}
