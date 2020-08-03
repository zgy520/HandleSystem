package com.zgy.handle.userService.controller.param.dict.update;

import com.zgy.handle.userService.controller.base.UpdateController;
import com.zgy.handle.userService.controller.param.convert.DictMapper;
import com.zgy.handle.userService.model.parameter.Dict;
import com.zgy.handle.userService.model.parameter.DictDTO;
import com.zgy.handle.userService.service.param.dict.query.DictQueryService;
import com.zgy.handle.userService.service.param.dict.update.DictUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "dict/update")
@Slf4j
public class DictUpdateController extends UpdateController<Dict, DictDTO> {
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
