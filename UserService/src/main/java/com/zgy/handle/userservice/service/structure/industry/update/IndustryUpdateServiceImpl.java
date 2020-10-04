package com.zgy.handle.userservice.service.structure.industry.update;

import com.zgy.handle.common.model.common.UniqueInfo;
import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.userservice.model.dto.structure.IndustryUpdateDTO;
import com.zgy.handle.userservice.model.structure.Industry;
import com.zgy.handle.userservice.repository.structure.industry.IndustryQueryRepository;
import com.zgy.handle.userservice.repository.structure.industry.IndustryUpdateRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
public class IndustryUpdateServiceImpl extends BaseUpdateServiceImpl<Industry, IndustryUpdateDTO> implements IndustryUpdateService {
    @Autowired
    private IndustryQueryRepository industryQueryRepository;
    private IndustryUpdateRepository industryUpdateRepository;

    public IndustryUpdateServiceImpl(IndustryUpdateRepository industryUpdateRepository) {
        super(industryUpdateRepository);
        this.industryUpdateRepository = industryUpdateRepository;
    }

    @Override
    public void fillRelateObj(IndustryUpdateDTO industryUpdateDTO, Industry industry) {
        if (StringUtils.isNotBlank(industryUpdateDTO.getParentId())) {
            Optional<Industry> industryOptional = baseRepository.findById(Long.valueOf(industryUpdateDTO.getParentId()));
            if (industryOptional.isPresent()) {
                industry.setParent(industryOptional.get());
            } else {
                throw new EntityNotFoundException("未发现id为:" + industryUpdateDTO.getParentId() + "得行业");
            }
        }
    }

    @Override
    public UniqueInfo checkUnique(IndustryUpdateDTO industryUpdateDTO, Industry industry) {
        Integer count = StringUtils.isBlank(industryUpdateDTO.getId()) ? industryQueryRepository.countByCodeOrName(industryUpdateDTO.getName(), industryUpdateDTO.getCode()) :
                industryQueryRepository.countByCodeOrNameAndIdNot(industryUpdateDTO.getName(), industryUpdateDTO.getCode(), Long.valueOf(industryUpdateDTO.getId()));
        if (count != null) {
            return UniqueInfo.getUniqueInfo("行业编码或名称不能重复");
        }
        return super.checkUnique(industryUpdateDTO, industry);
    }
}
