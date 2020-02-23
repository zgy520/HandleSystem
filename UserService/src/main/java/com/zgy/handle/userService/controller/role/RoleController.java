package com.zgy.handle.userService.controller.role;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.common.zuul.context.UserContext;
import com.zgy.handle.userService.controller.role.convert.RoleMapper;
import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.authority.RoleDTO;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.authority.RoleService;
import com.zgy.handle.userService.service.authority.RoleSpecificationsService;
import com.zgy.handle.userService.service.user.AccountService;
import com.zgy.handle.userService.util.Str.StrUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@Api("角色相关的接口信息")
@RequestMapping(value = "role")
@RequiredArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;
    private final RoleSpecificationsService roleSpecificationsService;
    private final HttpServletRequest request;
    private final RoleMapper roleMapper;
    private final AccountService accountService;

    /**
     * 获取角色列表
     * @param pageable
     * @param roleQuery
     * @return
     */
    @GetMapping(value = "list")
    public ResponseCode<List<RoleDTO>> getRoleList(@PageableDefault(page = 1,size = 10)Pageable pageable, Role roleQuery){
        pageable = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), Sort.Direction.DESC,"updateTime");
        ResponseCode<List<RoleDTO>> responseCode = ResponseCode.sucess();

        log.info("获取到的用户名为: " + request.getHeader(UserContext.USER_NAME));

        //Page<Role> roleList = roleService.findAllByExample(roleQuery,pageable);
        Page<Role> roleList = roleSpecificationsService.findAllByDynamicQuery(roleQuery,pageable);
        List<Role> roles = roleList.getContent();
        List<RoleDTO> roleDTOList = roleMapper.toRoleDTOs(roles);
        roleService.fetchAccountByRole(roleDTOList);
        responseCode.setPageInfo(roleList);
        responseCode.setData(roleDTOList);
        return responseCode;
    }

    @PostMapping(value = "update")
    public ResponseCode<String> update(@RequestBody RoleDTO roleDTO){
        ResponseCode<String> responseCode = ResponseCode.sucess();
        log.info("获取到的数据为:" + roleDTO);
        Role role = roleMapper.toRoleDTO(roleDTO);
        List<Long> userIdList = StrUtils.transformList(roleDTO.getUserList(),Long::parseLong);
        Set<Account> accountSet = accountService.findByIdIn(userIdList);
        role.setAccountSet(accountSet);
        roleService.update(StringUtils.isBlank(roleDTO.getId()) ? null : Long.valueOf(roleDTO.getId()),role);
        return responseCode;
    }

    @ApiOperation("根据id获取数据")
    @GetMapping(value = "find/{id}")
    public ResponseCode<RoleDTO> findById(@PathVariable(value = "id") Long id){
        ResponseCode<RoleDTO> responseCode = ResponseCode.sucess();
        Optional<Role> optionalRole = roleService.findById(id);
        if (!optionalRole.isPresent()){
            throw new EntityNotFoundException("角色中对应id={" + id + "}的数据不存在");
        }
        responseCode.setData(roleMapper.toRoleDTO(optionalRole.get()));
        return responseCode;
    }

    @ApiOperation("删除角色")
    @DeleteMapping(value = "delete/{id}")
    public ResponseCode<RoleDTO> delete(@PathVariable(value = "id") Long id){
        Optional<Role> optionalRole = roleService.findById(id);
        ResponseCode<RoleDTO> responseCode = ResponseCode.sucess();
        if (optionalRole.isPresent())
            responseCode.setData(roleMapper.toRoleDTO(optionalRole.get()));
        else
            throw new EntityNotFoundException("找不到id对应为:" + id.toString() + "的值");
        roleService.delete(id);
        return responseCode;
    }
    @GetMapping(value = "getRoleList")
    public ResponseCode<List<SelectDTO>> getRoleList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<Role> roleList = roleService.findAll();
        List<SelectDTO> selectDTOS = new ArrayList<>();
        roleList.stream().forEach(role -> {
            SelectDTO selectDTO = new SelectDTO(role.getId().toString(),role.getName());
            selectDTOS.add(selectDTO);
        });
        responseCode.setData(selectDTOS);
        return responseCode;
    }
}
