package com.zgy.handle.userservice.model.user.update;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author a4423
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateVo extends BaseDTO {

    @NotBlank(message = "名称不能为空")
    @Size(min = 2, max = 10, message = "名称必须在2到10个字符长度之间")
    private String name;
    @NotBlank(message = "登录名称不能为空")
    @Size(min = 2, max = 10,message = "登录名称必须在2到10各字符之间")
    private String loginName;

    @Email(message = "请输入正确的有限格式")
    private String email; // email地址
    private String note;
    private List<Long> roleIdList = new ArrayList<>();
    private List<Long> postIdList = new ArrayList<>();
    private Long departId; // 所属部门的id
    // 到期日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date expiredDate;
}
