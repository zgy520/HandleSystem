package com.zgy.handle.knowledge.service.linkpage;

import com.netflix.discovery.converters.Auto;
import com.zgy.handle.knowledge.model.catalog.Catalog;
import com.zgy.handle.knowledge.model.common.ResourceType;
import com.zgy.handle.knowledge.model.linkpage.LinkPage;
import com.zgy.handle.knowledge.model.linkpage.LinkPageDTO;
import com.zgy.handle.knowledge.repository.KnowledgeRepository;
import com.zgy.handle.knowledge.repository.linkpage.LinkPageRepository;
import com.zgy.handle.knowledge.service.KnowledgeService;
import com.zgy.handle.knowledge.service.catalog.CatalogService;
import com.zgy.handle.knowledge.service.dispatch.ResourceDispatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LinkPageService extends KnowledgeService<LinkPage, LinkPageDTO> {
    private LinkPageRepository linkPageRepository;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private ResourceDispatchService resourceDispatchService;
    @Autowired
    public LinkPageService(LinkPageRepository linkPageRepository) {
        super(linkPageRepository);
        this.linkPageRepository = linkPageRepository;
    }

    @Override
    public Page<LinkPage> findByDynamicQuery(Pageable pageable, LinkPageDTO dto) {
        Specification<LinkPage> specification = Specification
                .where(StringUtils.isBlank(dto.getTitle())?null:LinkPageRepository.blurStrQuery("title",dto.getTitle()))
                .and(StringUtils.isBlank(dto.getKeywords())?null:LinkPageRepository.blurStrQuery("keywords",dto.getKeywords()))
                .and(StringUtils.isBlank(dto.getCatalogName())?null:LinkPageRepository.findByCatalogName(dto.getCatalogName()))
                .and(StringUtils.isBlank(dto.getNote())?null:LinkPageRepository.blurStrQuery("note",dto.getNote()));

        return linkPageRepository.findAll(specification,pageable);
    }

    @Override
    public void beforeUpdate(LinkPageDTO linkPageDTO, LinkPage linkPage) {
        if (StringUtils.isNotBlank(linkPageDTO.getCatalogId())){
            Optional<Catalog> catalogOptional = catalogService.findById(Long.valueOf(linkPageDTO.getCatalogId()));
            if (catalogOptional.isPresent()){
                linkPage.setCatalog(catalogOptional.get());
            }
        }
    }

    @Override
    public void postUpdate(LinkPage linkPage, LinkPageDTO linkPageDTO) {
        resourceDispatchService.addResourceDispatch(linkPage.getId(), ResourceType.LINK);
    }
}
