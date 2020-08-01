package com.zgy.handle.userService.service.param.dict.query;

import com.zgy.handle.userService.model.parameter.Dict;
import com.zgy.handle.userService.model.parameter.DictDTO;
import com.zgy.handle.userService.model.parameter.Dict_;
import com.zgy.handle.userService.repository.param.dict.DictQueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DictQueryServiceImpl extends QueryServiceImpl<Dict, DictDTO> implements DictQueryService {
    private DictQueryRepository dictQueryRepository;
    public DictQueryServiceImpl(DictQueryRepository dictQueryRepository) {
        super(dictQueryRepository);
        this.dictQueryRepository = dictQueryRepository;
    }

    @Override
    public Specification<Dict> querySpecification(DictDTO dto) {
        Specification<Dict> specification = Specification
                .where(StringUtils.isNotBlank(dto.getName())?dictQueryRepository.fieldLike(Dict_.NAME,dto.getName()) : null)
                .and(StringUtils.isNotBlank(dto.getCode())?dictQueryRepository.fieldLike(Dict_.CODE,dto.getCode()) : null)
                .and(dictQueryRepository.rootDict());
        return specification;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dict> getChildrenDict(Long dictParentId) {
        List<Dict> childrenDict = dictQueryRepository.findAllByParentId(dictParentId);
        return childrenDict;
    }
}