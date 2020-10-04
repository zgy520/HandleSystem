package com.zgy.handle.userservice.controller.structure.depart.update;

import com.zgy.handle.common.controller.base.BaseUpdateController;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.model.authority.depart.DepartUpdateDTO;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.service.structure.depart.query.DepartQueryService;
import com.zgy.handle.userservice.service.structure.depart.update.DepartUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "depart/update")
@Slf4j
public class DepartUpdateController extends BaseUpdateController<Department, DepartUpdateDTO> {
    @Autowired
    private DepartUpdateMapper departUpdateMapper;
    private DepartUpdateService departUpdateService;
    private DepartQueryService departQueryService;

    @Autowired
    public DepartUpdateController(DepartUpdateService departUpdateService, DepartQueryService departQueryService) {
        super(departUpdateService, departQueryService);
        this.departQueryService = departQueryService;
        this.departUpdateService = departUpdateService;
    }

    @Override
    public Department convertUtoT(DepartUpdateDTO departUpdateDTO) {
        return departUpdateMapper.toDepartment(departUpdateDTO);
    }

    /**
     * 部门关联用户
     *
     * @param selectedUserList
     * @return
     */
    @PostMapping(value = "relateUser")
    public ResponseCode<String> relateUser(Long departId, String selectedUserList) {
        ResponseCode<String> responseCode = ResponseCode.sucess();
        log.info("角色ID为:" + departId.toString() + ",选择的用户为:" + selectedUserList);
        responseCode.setData(departUpdateService.relateUser(departId, selectedUserList));
        return responseCode;
    }

    /**
     * 部门关联岗位
     *
     * @param selectedPostList
     * @return
     */
    @PostMapping(value = "relatePost")
    public ResponseCode<String> relatePost(Long departId, String selectedPostList) {
        ResponseCode<String> responseCode = ResponseCode.sucess();
        log.info("角色ID为:" + departId.toString() + ",选择的岗位为:" + selectedPostList);
        responseCode.setData(departUpdateService.relatePost(departId, selectedPostList));
        return responseCode;
    }
}
