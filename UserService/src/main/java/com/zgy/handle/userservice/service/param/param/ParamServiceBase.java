package com.zgy.handle.userservice.service.param.param;

import com.zgy.handle.userservice.exception.ParamException;
import com.zgy.handle.userservice.model.common.UniqueInfo;
import com.zgy.handle.userservice.model.parameter.Param;
import com.zgy.handle.userservice.model.parameter.ParamDTO;
import com.zgy.handle.userservice.repository.param.ParamRepository;
import com.zgy.handle.userservice.service.BaseSystemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ParamServiceBase extends BaseSystemService<Param, ParamDTO> {
    private ParamRepository paramRepository;
    @Autowired
    public ParamServiceBase(ParamRepository paramRepository) {
        super(paramRepository);
        this.paramRepository = paramRepository;
    }

    @Override
    public Page<Param> findByDynamicQuery(Pageable pageable, ParamDTO dto) {
        Specification<Param> specification = Specification
                .where(StringUtils.isBlank(dto.getCode())? null : paramRepository.blurStrQuery("code",dto.getCode()))
                .and(StringUtils.isBlank(dto.getValue())?null : paramRepository.blurStrQuery("value",dto.getValue()))
                .and(dto.getParamType()==null?null: ParamRepository.filterParamType(dto.getParamType()))
                .and(ParamRepository.visibleParam())
                ;
        return paramRepository.findAll(specification,pageable);
    }

    @Override
    public UniqueInfo checkUnique(ParamDTO paramDTO, Param param) {
        Integer count = StringUtils.isBlank(paramDTO.getId())? paramRepository.countByCode(paramDTO.getCode()) : paramRepository.countByCode(paramDTO.getCode(),Long.valueOf(paramDTO.getId()));
        if (count != null && count > 0){
            return UniqueInfo.getUniqueInfo("存在编码为:" + paramDTO.getCode() + "的参数");
        }
        return UniqueInfo.getDefaultUnique();
    }

    @Override
    public void beforeUpdate(ParamDTO paramDTO, Param param) {
        if (paramDTO.getParamType() == null){
            throw new ParamException("请传入正确的参数类型");
        }
    }
}
