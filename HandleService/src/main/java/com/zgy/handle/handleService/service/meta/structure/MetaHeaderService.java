package com.zgy.handle.handleService.service.meta.structure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.common.util.tree.TreeConvert;
import com.zgy.handle.handleService.model.meta.dto.structure.MetaHeaderDTO;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import com.zgy.handle.handleService.repository.meta.structure.MetaHeaderRepository;
import com.zgy.handle.handleService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MetaHeaderService extends SystemService<MetaHeader, MetaHeaderDTO> {
    private MetaHeaderRepository metaHeaderRepository;
    @Autowired
    public MetaHeaderService(MetaHeaderRepository metaHeaderRepository) {
        super(metaHeaderRepository);
        this.metaHeaderRepository = metaHeaderRepository;
    }

    public JSONArray getHandleCodeAlisa(){
        JSONArray jsonArray = new JSONArray();
        List<MetaHeader> metaNodeList = metaHeaderRepository.findAll();
        for(MetaHeader metaNode : metaNodeList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("handleCode",metaNode.getHeader().getIdentityNum());
            jsonObject.put("alisa",metaNode.getHeader().getAlias());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * 根据企业id获取对应的元数据信息
     * @param enterpriseId
     * @return
     */
    public List<MetaHeader> getMetaHeaderListByEnterpriseId(Long enterpriseId){
        if (enterpriseId == null) {
            if (StringUtils.isNotBlank(this.getEnterpriseId()))
                enterpriseId = Long.valueOf(this.getEnterpriseId());
            else
                throw new EntityNotFoundException("未找到企业信息");
        }
        return metaHeaderRepository.findByEnterpriseId(enterpriseId);
    }

    public List<MetaHeaderDTO> getMetaHeaderDTOList(List<MetaHeaderDTO> metaHeaderDTOList){
        TreeConvert treeConvert = new TreeConvert(metaHeaderDTOList);
        try{
            List<MetaHeaderDTO> metaHeaderDTOS = treeConvert.toJsonArray(MetaHeaderDTO.class);
            return metaHeaderDTOS;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void beforeUpdate(MetaHeaderDTO metaHeaderDTO, MetaHeader metaHeader) {
        if (StringUtils.isNotBlank(metaHeaderDTO.getParentId())){
            Optional<MetaHeader> metaHeaderOptional = this.findById(Long.valueOf(metaHeaderDTO.getParentId()));
            if (metaHeaderOptional.isPresent()){
                metaHeader.setParent(metaHeaderOptional.get());
            }
        }
        log.info("获取到的企业id为：" + getDepartId());
        if(metaHeader.getEnterpriseId() == null){
            if (getDepartId() != null){
                metaHeader.setEnterpriseId(Long.valueOf(getDepartId()));
            }
        }
    }
}
