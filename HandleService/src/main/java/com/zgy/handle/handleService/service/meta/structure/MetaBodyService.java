package com.zgy.handle.handleService.service.meta.structure;

import com.zgy.handle.handleService.model.meta.dto.structure.MetaBodyDTO;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import com.zgy.handle.handleService.repository.meta.structure.MetaBodyRepository;
import com.zgy.handle.handleService.service.SystemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MetaBodyService extends SystemService<MetaBody, MetaBodyDTO> {
    private MetaBodyRepository metaBodyRepository;
    @Autowired
    private MetaHeaderService metaHeaderService;
    @Autowired
    public MetaBodyService(MetaBodyRepository metaBodyRepository) {
        super(metaBodyRepository);
        this.metaBodyRepository = metaBodyRepository;
    }

    public List<MetaBody> findByHeaderId(Long headerId){
        if (headerId == null){
            throw new EntityNotFoundException("请传入元数据头部id");
        }
        return metaBodyRepository.findByMetaHeaderId(headerId);
    }

    @Override
    public void beforeUpdate(MetaBodyDTO metaBodyDTO, MetaBody metaBody) {
        if (StringUtils.isNotBlank(metaBodyDTO.getHeaderId())){
            Optional<MetaHeader> metaHeaderOptional = metaHeaderService.findById(Long.valueOf(metaBodyDTO.getHeaderId()));
            if (metaHeaderOptional.isPresent()){
                metaBody.setMetaHeader(metaHeaderOptional.get());
            }
        }
    }
}
