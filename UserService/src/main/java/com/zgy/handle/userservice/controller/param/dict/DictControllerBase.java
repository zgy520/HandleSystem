package com.zgy.handle.userservice.controller.param.dict;

import com.zgy.handle.userservice.controller.BaseSystemController;
import com.zgy.handle.userservice.controller.param.convert.DictMapper;
import com.zgy.handle.userservice.core.cache.DictCache;
import com.zgy.handle.userservice.core.cache.ParamCache;
import com.zgy.handle.userservice.model.parameter.Dict;
import com.zgy.handle.userservice.model.parameter.DictDTO;
import com.zgy.handle.userservice.model.user.SelectDTO;
import com.zgy.handle.userservice.service.param.dict.DictServiceBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "dict")
@Slf4j
public class DictControllerBase extends BaseSystemController<Dict, DictDTO> {
    private DictServiceBase dictService;
    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private ParamCache paramCache;
    @Autowired
    private DictCache dictCache;

    @Autowired
    public DictControllerBase(DictServiceBase dictService) {
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


    @GetMapping(value = "test")
    public void testCache(String code) {
        dictCache.getValueByKey(code).stream().forEach(dict -> System.out.println(((Dict) dict).getName()));
    }

    @GetMapping(value = "param")
    public void paramCache(String code) {
        log.info(paramCache.getValueByKey("fax"));
    }
}
