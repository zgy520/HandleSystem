package com.zgy.handle.userService.controller.param.dict;

import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.param.convert.DictMapper;
import com.zgy.handle.userService.model.parameter.Dict;
import com.zgy.handle.userService.model.parameter.DictDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.param.dict.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "dict")
@Slf4j
public class DictController extends SystemController<Dict, DictDTO> {
    private DictService dictService;
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    public DictController(DictService dictService) {
        super(dictService);
        this.dictService = dictService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Dict> dicts) {
        return null;
    }

    @Override
    public List<DictDTO> convertTtoU(List<Dict> dicts) {
        return dictMapper.toDictDtoList(dicts);
    }

    @Override
    public DictDTO convertTtoU(Dict dict) {
        return dictMapper.toDictDto(dict);
    }

    @Override
    public Dict convertUtoT(DictDTO dictDTO) {
        return dictMapper.toDict(dictDTO);
    }
}
