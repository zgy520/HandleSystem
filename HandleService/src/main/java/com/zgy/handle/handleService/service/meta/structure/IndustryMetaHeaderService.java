package com.zgy.handle.handleService.service.meta.structure;

import com.zgy.handle.common.util.tree.TreeConvert;
import com.zgy.handle.handleService.model.meta.dto.structure.IndustryMetaHeaderDTO;
import com.zgy.handle.handleService.model.meta.dto.structure.MetaHeaderDTO;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import com.zgy.handle.handleService.model.meta.structure.industry.IndustryMetaHeader;
import com.zgy.handle.handleService.repository.meta.structure.IndustryMetaHeaderRepository;
import com.zgy.handle.handleService.service.SystemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class IndustryMetaHeaderService extends SystemService<IndustryMetaHeader, IndustryMetaHeaderDTO> {
    private IndustryMetaHeaderRepository industryMetaHeaderRepository;
    @Autowired
    public IndustryMetaHeaderService(IndustryMetaHeaderRepository industryMetaHeaderRepository) {
        super(industryMetaHeaderRepository);
        this.industryMetaHeaderRepository = industryMetaHeaderRepository;
    }
    public List<IndustryMetaHeaderDTO> getIndustryMetaHeaderDTOList(List<IndustryMetaHeaderDTO> industryMetaHeaderDTOList){
        TreeConvert treeConvert = new TreeConvert(industryMetaHeaderDTOList);
        try{
            List<IndustryMetaHeaderDTO> industryMetaHeaderDTOS = treeConvert.toJsonArray(IndustryMetaHeaderDTO.class);
            return industryMetaHeaderDTOS;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public IndustryMetaHeader findByCode(String handleCode){
        List<IndustryMetaHeader> industryMetaHeaderList = industryMetaHeaderRepository.findByHeader_IdentityNum(handleCode);
        if (industryMetaHeaderList.size() > 0){
            return industryMetaHeaderList.get(0);
        }else {
            throw new EntityNotFoundException("handle码未找到:"+handleCode);
        }
    }

    /**
     * 根据行业id获取所有的元数据标准
     * @param industryId
     * @return
     */
    public List<IndustryMetaHeader> findByIndustryId(Long industryId){
        if (industryId == null){
            throw new EntityNotFoundException("请传入对应的企业id");
        }
        return industryMetaHeaderRepository.findByIndustryId(industryId);
    }

    @Override
    public void beforeUpdate(IndustryMetaHeaderDTO industryMetaHeaderDTO, IndustryMetaHeader industryMetaHeader) {
        if (StringUtils.isNotBlank(industryMetaHeaderDTO.getParentId())){
            Optional<IndustryMetaHeader> industryMetaHeaderOptional = this.findById(Long.valueOf(industryMetaHeaderDTO.getParentId()));
            if (industryMetaHeaderOptional.isPresent()){
                industryMetaHeader.setParent(industryMetaHeaderOptional.get());
            }
        }
    }
}
