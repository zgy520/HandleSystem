package com.zgy.handle.handleService.service.meta.bus;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.handleService.excelTools.ExcelImpl;
import com.zgy.handle.handleService.excelTools.ExcelTools;
import com.zgy.handle.handleService.model.meta.bus.BusPrimary;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import com.zgy.handle.handleService.service.Import.TemplateUploadService;
import com.zgy.handle.handleService.service.meta.structure.MetaBodyService;
import com.zgy.handle.handleService.service.meta.structure.MetaHeaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BusinessDataUploadService extends TemplateUploadService<BusPrimary> {
    @Autowired
    private MetaHeaderService metaHeaderService;
    @Autowired
    private MetaBodyService metaBodyService;
    @Override
    public JSONArray handleData(JSONArray jsonArray, String attachData) {
        JSONArray failArray = new JSONArray();
        log.info(jsonArray.toJSONString());
        Optional<MetaHeader> metaHeaderOptional = metaHeaderService.findById(Long.valueOf(attachData));
        if (metaHeaderOptional.isPresent()){
            MetaHeader metaHeader = metaHeaderOptional.get();
            List<MetaBody> metaBodyList = metaBodyService.findByHeaderId(metaHeader.getId());
            JSONArray dataConvertedArray =convertOptionsToField(jsonArray,metaBodyList);
            log.info("转化后的数据为: " + dataConvertedArray);
        }else {
            throw new EntityNotFoundException("不存在id为{" + attachData + "}的元数据标准");
        }

        return failArray;
    }

    /**
     * 将JsonArray中的option转换为其对应的字段
     * @param jsonArray
     * @param metaDataList
     * @return
     */
    private JSONArray convertOptionsToField(JSONArray jsonArray, List<MetaBody> metaDataList){

        JSONObject headerJson = jsonArray.getJSONObject(0); // 获取表头
        JSONObject convertedHeaderJson = new JSONObject();
        int headerSize = ExcelTools.getOptionCount(headerJson, ExcelImpl.ATTACH_OPTION);
        // 将头部的值转化为字段
        for (int i = 1; i <= headerSize; i++){
            String title = headerJson.getString(ExcelImpl.ATTACH_OPTION + i);
            Optional<MetaBody> metaDataOptional = metaDataList.stream().filter(md->md.getBody().getName().equals(title)).findFirst();
            if (metaDataOptional.isPresent()){
                //headerJson.put(title,metaDataOptional.get().getName());
                convertedHeaderJson.put(ExcelImpl.ATTACH_OPTION + i,metaDataOptional.get().getBody().getName());
            }
        }
        JSONArray convertedJsonArray = new JSONArray();
        for (int i = 1; i < jsonArray.size(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONObject convertedJson = new JSONObject();
            for (int headerIndex = 1; headerIndex <= headerSize; headerIndex++){
                String oldKey = ExcelImpl.ATTACH_OPTION + headerIndex;
                convertedJson.put(convertedHeaderJson.getString(oldKey),jsonObject.getString(oldKey));
            }
            convertedJsonArray.add(convertedJson);
        }
        log.info("转换后的标题为:" + convertedHeaderJson.toJSONString());
        return convertedJsonArray;
    }
}
