package com.zgy.handle.userservice.service.param.dict;

import com.zgy.handle.userservice.exception.ParamException;
import com.zgy.handle.userservice.model.common.UniqueInfo;
import com.zgy.handle.userservice.model.parameter.Dict;
import com.zgy.handle.userservice.model.parameter.DictDTO;
import com.zgy.handle.userservice.repository.param.DictRepository;
import com.zgy.handle.userservice.service.BaseSystemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DictServiceBase extends BaseSystemService<Dict, DictDTO> {
    private DictRepository dictRepository;
    @Autowired
    public DictServiceBase(DictRepository dictRepository) {
        super(dictRepository);
        this.dictRepository = dictRepository;
    }

    @Override
    public Page<Dict> findByDynamicQuery(Pageable pageable, DictDTO dto) {
        Specification<Dict> specification = Specification
                .where(StringUtils.isNotBlank(dto.getCode())?dictRepository.blurStrQuery("code",dto.getCode()):null)
                .and(StringUtils.isNotBlank(dto.getName())?dictRepository.blurStrQuery("name",dto.getName()):null)
                .and(dto.getDictType() == null? null:DictRepository.filterDictType(dto.getDictType()));
        return dictRepository.findAll(specification, pageable);
    }

    /**
     * 根据code获取对应的下级列表
     * 仅支持一级
     * @param code
     * @return
     */
    public Optional<List<String>> getByCode(String code){
        Optional<Dict> dict = dictRepository.findByCode(code);
        if (dict.isPresent()){
            Dict dit = dict.get();
            List<Dict> dictList = dictRepository.findByParentId(dit.getId());
            List<String> list = new ArrayList<>();
            dictList.stream().forEach(dict1 -> {
                list.add(dict1.getName());
            });
            return Optional.of(list);
        }
        return Optional.empty();
    }


    @Override
    public UniqueInfo checkUnique(DictDTO dictDTO, Dict dict) {
        Integer count = dictDTO.getId() == null? dictRepository.countByCode(dictDTO.getCode()) : dictRepository.countByCode(dictDTO.getCode(),Long.valueOf(dictDTO.getId()));

        if (count != null && count > 0){
            return UniqueInfo.getUniqueInfo("字典编码[" + dictDTO.getCode() + "]已存在");
        }

        return UniqueInfo.getDefaultUnique();
    }

    @Override
    public void beforeUpdate(DictDTO dictDTO, Dict dict) {
        if (dict.getDictType() == null){
            throw new ParamException("请传入正确的字典类型");
        }
        if (dictDTO.getParentId() != null){
            Optional<Dict> parentOptional = dictRepository.findById(dictDTO.getParentId());
            if (parentOptional.isPresent()){
                dict.setParent(parentOptional.get());
            }
        }
    }
}
