package com.zgy.handle.knowledge.service.catalog;

import com.zgy.handle.common.util.tree.TreeConvert;
import com.zgy.handle.knowledge.model.PostList;
import com.zgy.handle.knowledge.model.catalog.Catalog;
import com.zgy.handle.knowledge.model.catalog.CatalogDTO;
import com.zgy.handle.knowledge.model.common.ResourceLevel;
import com.zgy.handle.knowledge.repository.catalog.CatalogRepository;
import com.zgy.handle.knowledge.service.KnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CatalogService extends KnowledgeService<Catalog, CatalogDTO> {
    private CatalogRepository catalogRepository;
    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        super(catalogRepository);
        this.catalogRepository = catalogRepository;
    }

    /**
     * 获取属性列表
     * @param catalogList
     * @return
     * @throws Exception
     */
    public List<CatalogDTO> getCatalogDTOTreeList(List<CatalogDTO> catalogList) throws Exception {
        TreeConvert treeConvert = new TreeConvert(catalogList);
        return treeConvert.toJsonArray(CatalogDTO.class);
    }

    @Override
    public void beforeUpdate(CatalogDTO catalogDTO, Catalog catalog) {
        if (StringUtils.isNotBlank(catalogDTO.getParentId())){
            Optional<Catalog> catalogOptional = catalogRepository.findById(Long.valueOf(catalogDTO.getParentId()));
            if (catalogOptional.isPresent()){
                catalog.setParent(catalogOptional.get());
            }
        }
        if (catalog.getId() == null){
            String postCodeList = this.getPostName();
            if (postCodeList.contains(PostList.Admin.toString())){
                catalog.setResourceLevel(ResourceLevel.ENTERPRISE);
                catalog.setBusinessId(Long.valueOf(this.getEnterpriseId()));
            }else if (postCodeList.contains(PostList.departLeader.toString())){
                catalog.setResourceLevel(ResourceLevel.DEPART);
                catalog.setBusinessId(Long.valueOf(this.getDepartId()));
            }else {
                catalog.setResourceLevel(ResourceLevel.PERSONAL);
                catalog.setBusinessId(Long.valueOf(this.getPersonalId()));
            }
        }else {
            Catalog oldCatalog = catalogRepository.findById(catalog.getId()).get();
            catalog.setBusinessId(oldCatalog.getBusinessId());
            catalog.setResourceLevel(oldCatalog.getResourceLevel());

        }
    }
}
