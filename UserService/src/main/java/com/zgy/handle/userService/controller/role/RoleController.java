package com.zgy.handle.userService.controller.role;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.common.zuul.context.UserContext;
import com.zgy.handle.userService.controller.role.convert.RoleMapper;
import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.authority.RoleDTO;
import com.zgy.handle.userService.service.authority.RoleService;
import com.zgy.handle.userService.service.authority.RoleSpecificationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
        responseCode.setPageInfo(roleList);
        responseCode.setData(roleDTOList);
        return responseCode;
    }

    @PostMapping(value = "update")
    public ResponseCode<String> update(@RequestBody Role role){
        ResponseCode<String> responseCode = new ResponseCode<>();
        log.info("获取到的数据为:" + role);
        roleService.update(role.getId(),role);
        responseCode.setCode(20000);
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
}
