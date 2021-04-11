package com.zgy.handle.knowledge.controller.catalog;

import com.zgy.handle.common.model.common.TreeSelectDTO;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.knowledge.controller.KnowledgeController;
import com.zgy.handle.knowledge.controller.catalog.convert.CatalogMapper;
import com.zgy.handle.knowledge.model.SelectDTO;
import com.zgy.handle.knowledge.model.catalog.Catalog;
import com.zgy.handle.knowledge.model.catalog.CatalogDTO;
import com.zgy.handle.knowledge.service.catalog.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "catalog")
@Slf4j
public class CatalogController extends KnowledgeController<Catalog, CatalogDTO> {
    private CatalogService catalogService;
    @Autowired
    private CatalogMapper catalogMapper;
    @Autowired
    public CatalogController(CatalogService catalogService) {
        super(catalogService);
        this.catalogService = catalogService;
    }

    @Override
    public ResponseCode<List<CatalogDTO>> list(Pageable pageable, CatalogDTO dto) {
        ResponseCode<List<CatalogDTO>> responseCode = ResponseCode.sucess();
        try {
            List<Catalog> catalogList = catalogService.findAll();
            responseCode.setData(catalogService.getCatalogDTOTreeList(catalogMapper.toCatalogDTOS(catalogList)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Catalog> catalogList) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        catalogList.stream().forEach(catalog -> {
            SelectDTO selectDTO = new SelectDTO(catalog.getId().toString(),catalog.getName());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }


    @GetMapping(value = "treeSelectList")
    public ResponseCode<List<TreeSelectDTO>> treeSelectList(){
        ResponseCode<List<TreeSelectDTO>> responseCode = ResponseCode.sucess();
        List<Catalog> tList = catalogService.findAll();
        List<TreeSelectDTO> selectDTOS = catalogMapper.toTreeSelectDTOList(tList);
        responseCode.setData(catalogService.getTreeSelectDTOList(selectDTOS));
        return responseCode;
    }

    @Override
    public List<CatalogDTO> convertTtoU(List<Catalog> catalogList) {
        return catalogMapper.toCatalogDTOS(catalogList);
    }

    @Override
    public CatalogDTO convertTtoU(Catalog catalog) {
        return catalogMapper.toCatalogDTO(catalog);
    }

    @Override
    public Catalog convertUtoT(CatalogDTO catalogDTO) {
        return catalogMapper.toCatalog(catalogDTO);
    }
}
