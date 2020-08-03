package com.zgy.handle.userService.service.param.param.query;

import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.model.parameter.ParamDTO;
import com.zgy.handle.userService.model.parameter.Param_;
import com.zgy.handle.userService.repository.param.param.ParamQueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParamQueryServiceImpl extends QueryServiceImpl<Param, ParamDTO> implements ParamQueryService {
    private ParamQueryRepository paramQueryRepository;
    public ParamQueryServiceImpl(ParamQueryRepository paramQueryRepository) {
        super(paramQueryRepository);
        this.paramQueryRepository = paramQueryRepository;
    }

    @Override
    public Specification<Param> querySpecification(ParamDTO dto) {
        Specification<Param> specification = Specification.where(StringUtils.isNotBlank(dto.getCode())?paramQueryRepository.fieldLike(Param_.CODE,dto.getCode()):null)
                .and(StringUtils.isNotBlank(dto.getValue())?paramQueryRepository.fieldLike(Param_.VALUE,dto.getValue()):null)
                .and(StringUtils.isNotBlank(dto.getNote())?paramQueryRepository.fieldLike(Param_.NOTE,dto.getNote()):null);
        return specification;
    }
}
