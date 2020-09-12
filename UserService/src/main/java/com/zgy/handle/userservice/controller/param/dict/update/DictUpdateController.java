package com.zgy.handle.userservice.controller.param.dict.update;

import com.zgy.handle.userservice.controller.base.BaseUpdateController;
import com.zgy.handle.userservice.controller.param.convert.DictMapper;
import com.zgy.handle.userservice.model.parameter.Dict;
import com.zgy.handle.userservice.model.parameter.DictDTO;
import com.zgy.handle.userservice.service.param.dict.query.DictQueryService;
import com.zgy.handle.userservice.service.param.dict.update.DictUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "dict/update")
@Slf4j
public class DictUpdateController extends BaseUpdateController<Dict, DictDTO> {
    @Autowired
    private DictMapper dictMapper;
    private DictQueryService dictQueryService;
    private DictUpdateService dictUpdateService;
    @Autowired
    public DictUpdateController(DictUpdateService dictUpdateService, DictQueryService dictQueryService) {
        super(dictUpdateService, dictQueryService);
        this.dictQueryService = dictQueryService;
        this.dictUpdateService = dictUpdateService;
    }

    @Override
    public Dict convertUtoT(DictDTO dictDTO) {
        return dictMapper.toDict(dictDTO);
    }
}
