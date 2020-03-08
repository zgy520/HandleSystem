package com.zgy.handle.handleService.service.meta.bus;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.model.meta.bus.BusDetails;
import com.zgy.handle.handleService.model.meta.bus.BusPrimary;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import com.zgy.handle.handleService.repository.meta.bus.BusDetailsRepository;
import com.zgy.handle.handleService.repository.meta.bus.BusPrimaryRepository;
import com.zgy.handle.handleService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BusPrimaryService extends SystemService<BusPrimary,BusPrimary> {
    private BusPrimaryRepository busPrimaryRepository;
    @Autowired
    private BusDetailsRepository busDetailsRepository;
    @Autowired
    public BusPrimaryService(BusPrimaryRepository busPrimaryRepository) {
        super(busPrimaryRepository);
        this.busPrimaryRepository = busPrimaryRepository;
    }

    public void saveBusinessData(MetaHeader metaHeader, JSONArray jsonArray, List<MetaBody> metaBodyList){
        long maxPriamry = busPrimaryRepository.findByMetaHeaderId(metaHeader.getId())
                .stream().mapToLong(BusPrimary::getPrimaryValue).max().orElse(0L);
        int size = jsonArray.size();
        List<BusDetails> detailsList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            BusPrimary busPrimary = saveBusPrimary(metaHeader,++maxPriamry);
            List<BusDetails> busDetails = getDetails(busPrimary,jsonObject,metaBodyList);
            detailsList.addAll(busDetails);
        }
        busDetailsRepository.saveAll(detailsList);
    }
    private List<BusDetails> getDetails(BusPrimary busPrimary,JSONObject jsonObject,List<MetaBody> metaBodies){
        List<BusDetails> busDetailsList = new ArrayList<>();
        for (MetaBody metaBody : metaBodies){
            BusDetails busDetails = new BusDetails();
            if (jsonObject.containsKey(metaBody.getBody().getName())){
                busDetails.setBusPrimary(busPrimary);
                busDetails.setFieldValue(jsonObject.getString(metaBody.getBody().getName()));
                busDetails.setMetaBody(metaBody);
                busDetailsList.add(busDetails);
            }
        }
        return busDetailsList;
    }

    private BusPrimary saveBusPrimary(MetaHeader header,Long primary){
        BusPrimary busPrimary = new BusPrimary();
        busPrimary.setMetaHeader(header);
        busPrimary.setPrimaryValue(primary);
        busPrimary.setHandleCode(header.getHeader().getIdentityNum() + "_" + primary);
        busPrimaryRepository.save(busPrimary);
        return busPrimary;
    }
}
