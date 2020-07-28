package com.zgy.handle.userService.service.param.dict.update;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.zgy.handle.userService.model.parameter.Dict;
import com.zgy.handle.userService.model.parameter.DictDTO;
import com.zgy.handle.userService.repository.param.dict.DictQueryRepository;
import com.zgy.handle.userService.repository.param.dict.DictUpdateRepository;
import com.zgy.handle.userService.service.base.impl.UpdateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
public class DictUpdateServiceImpl extends UpdateServiceImpl<Dict, DictDTO> implements DictUpdateService {
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
     * @param dictDTO
     * @param dict
     */
    @Override
    public void fillRelateObj(DictDTO dictDTO, Dict dict) {
        if (StringUtils.isNotBlank(dictDTO.getParentId())){
            Optional<Dict> parentDict = dictQueryRepository.findById(Long.valueOf(dictDTO.getParentId()));
            if (parentDict.isPresent()) {
                dict.setParent(parentDict.get());
            }
        }
    }
}
