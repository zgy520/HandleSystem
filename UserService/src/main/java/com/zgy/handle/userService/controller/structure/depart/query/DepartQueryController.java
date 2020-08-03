package com.zgy.handle.userService.controller.structure.depart.query;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.base.QueryController;
import com.zgy.handle.userService.model.authority.depart.DepartQueryDTO;
import com.zgy.handle.userService.model.common.TransferDTO;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.structure.depart.query.DepartQueryService;
import com.zgy.handle.userService.service.structure.depart.update.DepartUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "depart/query")
@Slf4j
public class DepartQueryController extends QueryController<Department, DepartQueryDTO> {
    @Autowired
    private DepartQueryMapper departQueryMapper;
    private DepartUpdateService departUpdateService;
    private DepartQueryService departQueryService;
    @Autowired
    public DepartQueryController(DepartUpdateService departUpdateService, DepartQueryService departQueryService) {
        super(departUpdateService, departQueryService);
        this.departQueryService = departQueryService;
        this.departUpdateService = departUpdateService;
    }

    @Override
    public ResponseCode<List<DepartQueryDTO>> list(Pageable pageable, DepartQueryDTO dto) {
        ResponseCode<List<DepartQueryDTO>> responseCode = ResponseCode.sucess();
        List<Department> departmentList = departQueryService.findAll();
        List<DepartQueryDTO> departmentDTOList = departQueryMapper.toDepartQueryDTOs(departmentList);
        responseCode.setData(departQueryService.getDepartmentDtoList(departmentDTOList));
        return responseCode;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Department> departments) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        departments.stream().forEach(department -> {
            SelectDTO selectDTO = new SelectDTO();
            selectDTO.setLabel(department.getName());
            selectDTO.setValue(department.getId().toString());
            selectDTO.setKey(department.getId().toString());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @Override
    public List<DepartQueryDTO> convertTtoU(List<Department> departments) {
        return departQueryMapper.toDepartQueryDTOs(departments);
    }

    @Override
    public DepartQueryDTO convertTtoU(Department department) {
        return departQueryMapper.toDepartQueryDTO(department);
    }

    @Override
    public Department convertUtoT(DepartQueryDTO departQueryDTO) {
        return departQueryMapper.toDepartment(departQueryDTO);
    }

    @GetMapping(value = "getAccountListByDepartId")
    public ResponseCode<List<TransferDTO>> getAccountListByDepartId(Long departId) {
        ResponseCode<List<TransferDTO>> responseCode = ResponseCode.sucess();
        responseCode.setData(departQueryService.getAccountListByDepartId(departId));
        return responseCode;
    }

    /**
     * 根据企业ID获取关联的岗位
     * @param departId
     * @return
     */
    @GetMapping(value = "getPostListByDepartId")
    public ResponseCode<List<TransferDTO>> getPostListByDepartId(Long departId){
        ResponseCode<List<TransferDTO>> responseCode = ResponseCode.sucess();
        responseCode.setData(departQueryService.getPostListByDepartId(departId));
        return responseCode;
    }
}
