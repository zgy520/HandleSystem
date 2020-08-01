package com.zgy.handle.userService.service.structure.industry.query;

import com.zgy.handle.userService.model.dto.structure.IndustryQueryDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.structure.Industry_;
import com.zgy.handle.userService.repository.structure.industry.IndustryQueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import com.zgy.handle.userService.util.tree.TreeConvert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IndustryQueryServiceImpl extends QueryServiceImpl<Industry, IndustryQueryDTO> implements IndustryQueryService {
    private IndustryQueryRepository industryQueryRepository;

    public IndustryQueryServiceImpl(IndustryQueryRepository industryQueryRepository) {
        super(industryQueryRepository);
        this.industryQueryRepository = industryQueryRepository;
    }


    @Override
    public List<IndustryQueryDTO> getTreeIndustryList(List<IndustryQueryDTO> industryQueryDTOS, IndustryQueryDTO dto) {
        TreeConvert treeConvert = new TreeConvert(industryQueryDTOS);
        try {
            return treeConvert.toJsonArray(IndustryQueryDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Industry> findBySpecificator(IndustryQueryDTO industryQueryDTO) {
        return industryQueryRepository.findAll(querySpecification(industryQueryDTO));
    }

    @Override
    public Specification<Industry> querySpecification(IndustryQueryDTO dto) {
        Specification<Industry> specification = Specification.where(StringUtils.isNotBlank(dto.getName()) ? industryQueryRepository.fieldLike(Industry_.NAME, dto.getName()) : null)
                .and(StringUtils.isNotBlank(dto.getCode()) ? industryQueryRepository.fieldLike(Industry_.CODE, dto.getCode()) : null)
                .and(StringUtils.isNotBlank(dto.getShortName()) ? industryQueryRepository.fieldLike(Industry_.SHORT_NAME, dto.getShortName()) : null);
        return specification;
    }
}
