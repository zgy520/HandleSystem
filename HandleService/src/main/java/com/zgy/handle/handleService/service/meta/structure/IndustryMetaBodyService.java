package com.zgy.handle.handleService.service.meta.structure;

import com.zgy.handle.handleService.model.meta.dto.structure.IndustryMetaBodyDTO;
import com.zgy.handle.handleService.model.meta.structure.industry.IndustryMetaBody;
import com.zgy.handle.handleService.model.meta.structure.industry.IndustryMetaHeader;
import com.zgy.handle.handleService.repository.meta.structure.IndustryMetaBodyRepository;
import com.zgy.handle.handleService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IndustryMetaBodyService extends SystemService<IndustryMetaBody, IndustryMetaBodyDTO> {
    private IndustryMetaBodyRepository industryMetaBodyRepository;
    @Autowired
    private IndustryMetaHeaderService industryMetaHeaderService;
    public IndustryMetaBodyService(IndustryMetaBodyRepository industryMetaBodyRepository) {
        super(industryMetaBodyRepository);
        this.industryMetaBodyRepository = industryMetaBodyRepository;
    }

    /**
     * 根据行业元数据的id获取字段信息
     * @param industryHeadreId
     * @return
     */
    public List<IndustryMetaBody> findByIndustryHeaderId(Long industryHeadreId){
        return industryMetaBodyRepository.findByIndustryMetaHeaderId(industryHeadreId);
    }

    @Override
    public void beforeUpdate(IndustryMetaBodyDTO industryMetaBodyDTO, IndustryMetaBody industryMetaBody) {
        if (StringUtils.isNotBlank(industryMetaBodyDTO.getHeaderId())){
            Optional<IndustryMetaHeader> industryMetaHeaderOptional = industryMetaHeaderService.findById(Long.valueOf(industryMetaBodyDTO.getHeaderId()));
            if (industryMetaHeaderOptional.isPresent()){
                industryMetaBody.setIndustryMetaHeader(industryMetaHeaderOptional.get());
            }
        }
    }
}
