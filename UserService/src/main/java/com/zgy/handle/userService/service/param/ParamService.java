package com.zgy.handle.userService.service.param;

import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.model.parameter.ParamDTO;
import com.zgy.handle.userService.model.parameter.ParamType;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.repository.param.ParamRepository;
import com.zgy.handle.userService.service.SystemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParamService extends SystemService<Param, ParamDTO> {
    private ParamRepository paramRepository;
    @Autowired
    public ParamService(ParamRepository paramRepository) {
        super(paramRepository);
        this.paramRepository = paramRepository;
    }

    @Override
    public Page<Param> findByDynamicQuery(Pageable pageable, ParamDTO dto) {
        Specification<Param> specification = Specification
                .where(ParamRepository.typeFilter(dto.getParamType()))
                .and(StringUtils.isBlank(dto.getCode())?null: paramRepository.blurStrQuery("code",dto.getCode()))
                ;
        return paramRepository.findAll(specification,pageable);
    }

    /**
     * 根据code和参数类型获取参数值的列表
     * @param code
     * @param paramType
     * @return
     */
    public List<SelectDTO> getParamSelectDTOByCode(String code, ParamType paramType){
        List<SelectDTO> selectDTOList = new ArrayList<>();
        Param codeParam = paramRepository.findAllByCodeAndParamType(code,paramType);
        List<Param> paramList = paramRepository.findAll();
        paramList.parallelStream().filter(param -> param.getParent() != null && param.getParent().getId().equals(codeParam.getId())).forEach(param -> {
            SelectDTO selectDTO = new SelectDTO(param.getCode(),param.getValue());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @Override
    public void beforeUpdate(ParamDTO paramDTO, Param param) {
        if (paramDTO.getParamType() == null){
            throw new EntityNotFoundException("参数类型不能为空@");
        }
        param.setNote(paramDTO.getNote());
        if (StringUtils.isNotBlank(paramDTO.getParentId())){
            Optional<Param> parentOptional = this.findById(Long.valueOf(paramDTO.getParentId()));
            if (parentOptional.isPresent()){
                param.setParent(parentOptional.get());
            }
        }
        if (paramDTO.getId() != null){
            param.setUpdateTime(new Date());
            param.setUpdator(getPersonalName());
        }

    }
}
