package com.zgy.handle.userService.controller.structure;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.structure.convert.EnterpriseMapper;
import com.zgy.handle.userService.model.structure.*;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.structure.EnterpriseService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "enterprise")
@Slf4j
@Api(tags = "企业相关的信息维护")
public class EnterpriseController extends SystemController<Enterprise,EnterpriseDTO> {
    private EnterpriseService enterpriseService;
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    public EnterpriseController(EnterpriseService enterpriseService) {
        super(enterpriseService);
        this.enterpriseService = enterpriseService;
    }


    @GetMapping(value = "preList")
    public ResponseCode<List<EnterpriseRegShortDTO>> getCheckList(Pageable pageable,EnterpriseRegShortDTO enterpriseRegShortDTO){
        ResponseCode<List<EnterpriseDTO>> responseCode = ResponseCode.sucess();
        ResponseCode<List<EnterpriseRegShortDTO>> shorResponseCode = ResponseCode.sucess();
        EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
        enterpriseDTO.setPrefix(enterpriseRegShortDTO.getPrefix());
        enterpriseDTO.setName(enterpriseRegShortDTO.getName());
        enterpriseDTO.setCheckStatus(enterpriseRegShortDTO.getCheckStatus());
        Page<Enterprise> page = enterpriseService.findByDynamicQuery(pageable,enterpriseDTO);
        //List<EnterpriseDTO> enterpriseDTOList = enterpriseMapper.toEnterpriseDTOs(enterpriseList);
        List<EnterpriseRegShortDTO> list = new ArrayList<>();
        page.getContent().stream().forEach(content->{
            EnterpriseRegShortDTO enterpriseRegShortDTO1 = new EnterpriseRegShortDTO();
            enterpriseRegShortDTO1.setAuthorStatus(content.getAuthorStatus());
            enterpriseRegShortDTO1.setCheckStatus(content.getCheckStatus());
            enterpriseRegShortDTO1.setName(content.getName());
            enterpriseRegShortDTO1.setPrefix(content.getPrefix());
            list.add(enterpriseRegShortDTO1);
        });
        shorResponseCode.setPageInfo(page);
        shorResponseCode.setData(list);
        return shorResponseCode;
    }

    @RequestMapping(method = RequestMethod.POST,value = "getEnterprisePre")
    public EnterprisePre getEnterprisePre(@RequestBody String enterpriseId){
        Optional<Enterprise> enterpriseOptional = enterpriseService.findById(Long.valueOf(enterpriseId));
        if (enterpriseOptional.isPresent()){
            Enterprise enterprise = enterpriseOptional.get();
            EnterprisePre enterprisePre = new EnterprisePre(enterprise.getPrefix(),enterprise.getIp());
            return enterprisePre;
        }else {
            return null;
        }

    }

    /*@Override
    public ResponseCode<List<EnterpriseDTO>> list(Pageable pageable, EnterpriseDTO dto) {
        ResponseCode<List<EnterpriseDTO>> responseCode = ResponseCode.sucess();
        List<Enterprise> enterpriseList = enterpriseService.findAll();
        List<EnterpriseDTO> enterpriseDTOList = enterpriseMapper.toEnterpriseDTOs(enterpriseList);
        responseCode.setData(enterpriseService.getEnterpriseDtoList(enterpriseDTOList));
        return responseCode;
    }*/

    @Override
    public List<EnterpriseDTO> convertTtoU(List<Enterprise> enterpriseList) {
        return enterpriseMapper.toEnterpriseDTOs(enterpriseList);
    }

    @Override
    public EnterpriseDTO convertTtoU(Enterprise enterprise) {
        return enterpriseMapper.toEnterpriseDTO(enterprise);
    }

    @Override
    public Enterprise convertUtoT(EnterpriseDTO enterpriseDTO) {
        return enterpriseMapper.toEnterprise(enterpriseDTO);
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Enterprise> enterpriseList) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        enterpriseList.stream().forEach(enterprise -> {
            SelectDTO selectDTO = new SelectDTO(enterprise.getId().toString(),enterprise.getName());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
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
