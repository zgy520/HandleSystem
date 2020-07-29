package com.zgy.handle.userService.service.param.param.update;

import com.zgy.handle.userService.model.common.UniqueInfo;
import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.model.parameter.ParamDTO;
import com.zgy.handle.userService.repository.param.param.ParamQueryRepository;
import com.zgy.handle.userService.repository.param.param.ParamUpdateRepository;
import com.zgy.handle.userService.service.base.impl.UpdateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParamUpdateServiceImpl extends UpdateServiceImpl<Param, ParamDTO> implements ParamUpdateService {
    @Autowired
    private ParamQueryRepository paramQueryRepository;
    private ParamUpdateRepository paramUpdateRepository;
    public ParamUpdateServiceImpl(ParamUpdateRepository paramUpdateRepository) {
        super(paramUpdateRepository);
        this.paramUpdateRepository = paramUpdateRepository;
    }

    @Override
    public UniqueInfo checkUnique(ParamDTO paramDTO, Param param) {
        Long count = StringUtils.isBlank(paramDTO.getId())? paramQueryRepository.countByCode(paramDTO.getCode()) : paramQueryRepository.countByCodeAndIdNot(paramDTO.getCode(),Long.valueOf(paramDTO.getId()));
        if (count > 0){
            return UniqueInfo.getUniqueInfo("参数代码重复");
        }
        return super.checkUnique(paramDTO, param);
    }
}
