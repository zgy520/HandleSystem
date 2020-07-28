package com.zgy.handle.userService.controller.param.dict.query;

import com.zgy.handle.userService.controller.base.QueryController;
import com.zgy.handle.userService.controller.param.convert.DictMapper;
import com.zgy.handle.userService.model.parameter.Dict;
import com.zgy.handle.userService.model.parameter.DictDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.base.QueryService;
import com.zgy.handle.userService.service.base.UpdateService;
import com.zgy.handle.userService.service.param.dict.query.DictQueryService;
import com.zgy.handle.userService.service.param.dict.update.DictUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "dict/query")
@Slf4j
public class DictQueryController extends QueryController<Dict, DictDTO> {
    @Autowired
    private DictMapper dictMapper;
    private DictQueryService dictQueryService;
    private DictUpdateService dictUpdateService;
    @Autowired
    public DictQueryController(DictUpdateService dictUpdateService, DictQueryService dictQueryService) {
        super(dictUpdateService, dictQueryService);
        this.dictQueryService = dictQueryService;
        this.dictUpdateService = dictUpdateService;
    }

    @Override
    public void fillList(List<Dict> entityList, List<DictDTO> dtoList) {
        dtoList.stream().forEach(dictDTO -> {
            dictDTO.setChildren(dictMapper.toDictDtoList(dictQueryService.getChildrenDict(Long.valueOf(dictDTO.getId()))));
        });
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Dict> dictList) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        dictList.stream().filter(dict -> dict.getParent() == null).forEach(dict -> {
            SelectDTO selectDTO = new SelectDTO(dict.getId().toString(),dict.getName(),dict.getId().toString());
            selectDTOList.add(selectDTO);

        });
        return selectDTOList;
    }

    @Override
    public List<DictDTO> convertTtoU(List<Dict> dictList) {
        return dictMapper.toDictDtoList(dictList);
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
