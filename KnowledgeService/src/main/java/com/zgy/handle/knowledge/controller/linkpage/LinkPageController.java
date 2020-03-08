package com.zgy.handle.knowledge.controller.linkpage;

import com.zgy.handle.knowledge.controller.KnowledgeController;
import com.zgy.handle.knowledge.controller.linkpage.convert.LinkPageMapper;
import com.zgy.handle.knowledge.model.SelectDTO;
import com.zgy.handle.knowledge.model.linkpage.LinkPage;
import com.zgy.handle.knowledge.model.linkpage.LinkPageDTO;
import com.zgy.handle.knowledge.service.linkpage.LinkPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "linkPage")
@Slf4j
public class LinkPageController extends KnowledgeController<LinkPage, LinkPageDTO> {
    private LinkPageService linkPageService;
    @Autowired
    private LinkPageMapper linkPageMapper;
    @Autowired
    public LinkPageController(LinkPageService linkPageService) {
        super(linkPageService);
        this.linkPageService = linkPageService;
    }

    @Override
    public void fillList(List<LinkPage> entityList, List<LinkPageDTO> dtoList) {
        super.fillList(entityList, dtoList);
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<LinkPage> linkPages) {
        return null;
    }

    @Override
    public List<LinkPageDTO> convertTtoU(List<LinkPage> linkPages) {
        return linkPageMapper.toLinkPageDTOS(linkPages);
    }

    @Override
    public LinkPageDTO convertTtoU(LinkPage linkPage) {
        return linkPageMapper.toLinkPageDTO(linkPage);
    }

    @Override
    public LinkPage convertUtoT(LinkPageDTO linkPageDTO) {
        return linkPageMapper.toLinkPage(linkPageDTO);
    }
}
