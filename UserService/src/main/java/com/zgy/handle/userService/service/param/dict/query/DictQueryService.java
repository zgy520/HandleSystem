package com.zgy.handle.userService.service.param.dict.query;

import com.zgy.handle.userService.model.parameter.Dict;
import com.zgy.handle.userService.model.parameter.DictDTO;
import com.zgy.handle.userService.service.base.QueryService;

import java.util.List;

public interface DictQueryService extends QueryService<Dict, DictDTO> {
    /**
     * 获取字典下的所有子字典
     * @param dictParentId
     * @return
     */
    List<Dict> getChildrenDict(Long dictParentId);
}
