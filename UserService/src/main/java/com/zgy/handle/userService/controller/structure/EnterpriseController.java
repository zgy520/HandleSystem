package com.zgy.handle.userService.controller.structure;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.structure.convert.EnterpriseMapper;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.structure.EnterpriseService;
import com.zgy.handle.userService.service.structure.IndustryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "enterprise")
@Slf4j
@RequiredArgsConstructor
public class EnterpriseController {
    private final EnterpriseService enterpriseService;
    private final EnterpriseMapper enterpriseMapper;
    private final IndustryService industryService;

    @GetMapping(value = "list")
    public ResponseCode<List<EnterpriseDTO>> list(EnterpriseDTO enterpriseDTO){
        ResponseCode<List<EnterpriseDTO>> responseCode = ResponseCode.sucess();
        responseCode.setData(enterpriseService.getEnterpriseDtoList());
        return responseCode;
    }

    @PostMapping(value = "update")
    public ResponseCode<Enterprise> update(@RequestBody EnterpriseDTO enterpriseDTO){
        ResponseCode<Enterprise> responseCode = ResponseCode.sucess();
        Enterprise enterprise = enterpriseMapper.toEnterprise(enterpriseDTO);
        if (StringUtils.isNotBlank(enterpriseDTO.getParentId())){
            Optional<Enterprise> parentOptional = enterpriseService.findById(Long.valueOf(enterpriseDTO.getParentId()));
            if (parentOptional.isPresent()){
                enterprise.setParent(parentOptional.get());
            }
        }
        if (StringUtils.isNotBlank(enterpriseDTO.getIndustryId())){
            Optional<Industry> industryOptional = industryService.findById(Long.valueOf(enterpriseDTO.getIndustryId()));
            if (industryOptional.isPresent()){
                enterprise.setIndustry(industryOptional.get());
            }
        }
        enterprise.setNote(enterpriseDTO.getNote());
        enterprise = enterpriseService.update(StringUtils.isBlank(enterpriseDTO.getId())?null:Long.valueOf(enterpriseDTO.getId()),enterprise);
        responseCode.setData(enterprise);
        return responseCode;
    }
    @DeleteMapping(value = "delete/{id}")
    public ResponseCode<EnterpriseDTO> delete(@PathVariable("id") Long id){
        ResponseCode<EnterpriseDTO> responseCode = ResponseCode.sucess();
        Optional<Enterprise> optionalEnterprise = enterpriseService.findById(id);
        if (optionalEnterprise.isPresent()){
            responseCode.setData(enterpriseMapper.toEnterpriseDTO(optionalEnterprise.get()));
        }else {
            throw new EntityNotFoundException("找不到id为:" + id + "的数据");
        }
        enterpriseService.delete(id);
        return responseCode;
    }

    /**
     * 获取所有的企业列表，用于下拉框
     * @return
     */
    @GetMapping(value = "getEnterpriseList")
    public ResponseCode<List<SelectDTO>> getEnerpriseList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<Enterprise> enterpriseList = enterpriseService.findAll();
        List<SelectDTO> selectDTOList = new ArrayList<>();
        enterpriseList.stream().forEach(enterprise -> {
            SelectDTO selectDTO = new SelectDTO(enterprise.getId().toString(),enterprise.getName());
            selectDTOList.add(selectDTO);
        });
        responseCode.setData(selectDTOList);
        return responseCode;
    }
}
