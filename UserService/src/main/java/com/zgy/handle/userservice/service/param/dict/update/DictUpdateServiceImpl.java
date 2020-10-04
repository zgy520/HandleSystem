package com.zgy.handle.userservice.service.param.dict.update;

import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.userservice.model.parameter.Dict;
import com.zgy.handle.userservice.model.parameter.DictDTO;
import com.zgy.handle.userservice.repository.param.dict.DictQueryRepository;
import com.zgy.handle.userservice.repository.param.dict.DictUpdateRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DictUpdateServiceImpl extends BaseUpdateServiceImpl<Dict, DictDTO> implements DictUpdateService {
    @Autowired
    private DictQueryRepository dictQueryRepository;
    private DictUpdateRepository dictUpdateRepository;

    @Autowired
    public DictUpdateServiceImpl(DictUpdateRepository dictUpdateRepository) {
        super(dictUpdateRepository);
        this.dictUpdateRepository = dictUpdateRepository;
    }

    /**
     * 填充关联对象
     *
     * @param dictDTO
     * @param dict
     */
    @Override
    public void fillRelateObj(DictDTO dictDTO, Dict dict) {
        if (StringUtils.isNotBlank(dictDTO.getParentId())) {
            Optional<Dict> parentDict = dictQueryRepository.findById(Long.valueOf(dictDTO.getParentId()));
            if (parentDict.isPresent()) {
                dict.setParent(parentDict.get());
            }
        }
    }
}
