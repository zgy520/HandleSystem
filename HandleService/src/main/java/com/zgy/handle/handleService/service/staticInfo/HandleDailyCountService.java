package com.zgy.handle.handleService.service.staticInfo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
import com.zgy.handle.handleService.model.base.SecondRootInfo;
import com.zgy.handle.handleService.model.staticInfo.*;
import com.zgy.handle.handleService.repository.SystemRepository;
import com.zgy.handle.handleService.repository.base.SecondRootInfoRepository;
import com.zgy.handle.handleService.repository.staticInfo.HandleDailyCountRepository;
import com.zgy.handle.handleService.repository.staticInfo.HandleTotalRepository;
import com.zgy.handle.handleService.repository.staticInfo.MonthHandleTotalRepository;
import com.zgy.handle.handleService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HandleDailyCountService extends SystemService<HandleDailyCount, HandleDailyCountDTO> {
    //private static final String url = "";
    private HandleDailyCountRepository handleDailyCountRepository;
    @Autowired
    private SecondRootInfoRepository secondRootInfoRepository;
    @Autowired
    private HandleTotalRepository handleTotalRepository;
    @Autowired
    private MonthHandleTotalRepository monthHandleTotalRepository;

    public HandleDailyCountService(HandleDailyCountRepository handleDailyCountRepository) {
        super(handleDailyCountRepository);
        this.handleDailyCountRepository = handleDailyCountRepository;
    }

    /**
     * 获取当前月份每天的
     * @param handleType
     * @return
     */
    public JSONObject getMonthDays(HandleType handleType){
        JSONObject jsonObject = new JSONObject();
        LocalDate localDate = LocalDate.now();
        List<HandleDailyCount> monthHandleTotalList = handleDailyCountRepository.findAllByHandleTypeAndYearAndMonth(handleType,localDate.getYear(),localDate.getMonthValue())
                .stream().sorted(Comparator.comparing(HandleDailyCount::getRecordDate)).collect(Collectors.toList());
        String vals = monthHandleTotalList.stream().map(HandleDailyCount::getDailyCount).map(String::valueOf)
                .collect(Collectors.joining(","));
        jsonObject.put("data",vals);
        return jsonObject;
    }

    /**
     * 获取当前年没月的数量
     * @param handleType
     * @return
     */
    public JSONObject getYearMonths(HandleType handleType){
        JSONObject jsonObject = new JSONObject();
        LocalDate localDate = LocalDate.now();
        List<MonthHandleTotal> monthHandleTotalList = monthHandleTotalRepository.findAllByHandleTypeAndYear(handleType,localDate.getYear())
                .stream().sorted(Comparator.comparing(MonthHandleTotal::getMonth)).collect(Collectors.toList());
        String vals = monthHandleTotalList.stream().map(MonthHandleTotal::getTotalCount).map(String::valueOf)
                .collect(Collectors.joining(","));
        jsonObject.put("data",vals);
        return jsonObject;
    }


    public JSONObject getTotalByType(HandleType handleType){
        LocalDate localDate = LocalDate.now();
        Optional<HandleTotal> handleTotalOptional = handleTotalRepository.findByHandleType(handleType);
        Optional<MonthHandleTotal> monthHandleTotalOptional = monthHandleTotalRepository.findAllByHandleTypeAndYearAndMonth(handleType,localDate.getYear(),localDate.getMonthValue());
        JSONObject jsonObject = new JSONObject();
        if (handleTotalOptional.isPresent()){
            jsonObject.put("total",handleTotalOptional.get().getTotalCount());
        }else {
            jsonObject.put("total",0);
        }
        if (monthHandleTotalOptional.isPresent()){
            jsonObject.put("monthTotal",monthHandleTotalOptional.get().getTotalCount());
        }else {
            jsonObject.put("monthTotal",0);
        }
        return jsonObject;
    }


    public void updateDailyCount(){
        List<SecondRootInfo> secondRootInfos = secondRootInfoRepository.findAll();
        if (secondRootInfos == null || secondRootInfos.size() == 0){
            throw new EntityNotFoundException("未正确配置根节点服务器");
        }
        SecondRootInfo secondRootInfo = secondRootInfos.get(0);
        String url = "https://" + secondRootInfo.getParentIp() + ":" + secondRootInfo.getParentPort() + "/api/datastatistics/";

        OkHttpClient client = new OkHttpClient();
        log.info("请求的url为:" + url);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Handle version=0,sessionId=i7w4ospomdu3ymb7fev2r92o")
                .build();
        try{
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            log.info("获取到的数据未:" + body);
            JSONObject jsonObject = JSONObject.parseObject(body);
            log.info(jsonObject.toJSONString());
            if (jsonObject.getInteger("responseCode") == 1){
                int count = 0;
                int resolveCount = 0; // 解析量
                JSONArray jsonArray = jsonObject.getJSONArray("message");
                count = handleJsonArray(jsonArray,"business_count"); // 业务数据注册量
                resolveCount += handleJsonArray(jsonArray,"resolution_count");
                log.info("总数量为:" + count + " ,解析两为:" + resolveCount);
                updateCount(count,resolveCount);
            }
        }catch (IOException exception){

        }

    }


    /**
     * 更新对应的数量
     * @param regCount
     * @param resolveCount
     */
    public void updateCount(Integer regCount,Integer resolveCount){
        Optional<HandleTotal> handleRegTotalOptional = handleTotalRepository.findByHandleType(HandleType.REGISTER);
        Optional<HandleTotal> handleResolToalOptional = handleTotalRepository.findByHandleType(HandleType.RESOLVER);
        if (handleRegTotalOptional.isPresent()){
            // 更新当前的数量
            updateDayCount(HandleType.REGISTER,regCount);
            // 更新当月的数量
            updateMonthCount(HandleType.REGISTER,regCount);
            // 更新最终的总数量
            updateTotalCount(HandleType.REGISTER,regCount);
        }else {
            addHandleTotal(HandleType.REGISTER,regCount);
        }
        if (handleResolToalOptional.isPresent()){
            // 更新当前的数量
            updateDayCount(HandleType.RESOLVER,resolveCount);

            // 更新当月的数量
            updateMonthCount(HandleType.RESOLVER,resolveCount);
            // 更新最终的总数量
            updateTotalCount(HandleType.RESOLVER,resolveCount);
        }else {
            addHandleTotal(HandleType.RESOLVER,resolveCount);
        }
    }


    /**
     * 更新每天的数量
     * @param handleType
     * @param count
     */
    public void updateDayCount(HandleType handleType,Integer count){
        LocalDate curDay = LocalDate.now();
        Optional<HandleTotal> handleTotalOptional = handleTotalRepository.findByHandleType(handleType);
        Integer totalCount = handleTotalOptional.get().getTotalCount();
        count = count - totalCount;
        Optional<HandleDailyCount> handleDailyCountOptional = handleDailyCountRepository.findByHandleTypeAndRecordDate(handleType,curDay);
        if (handleDailyCountOptional.isPresent()){
            HandleDailyCount handleDailyCount = handleDailyCountOptional.get();
            handleDailyCount.setDailyCount(count);
            handleDailyCountRepository.save(handleDailyCount);
        }else {
            HandleDailyCount handleDailyCount = new HandleDailyCount(curDay,curDay.getYear(),curDay.getMonth().getValue(),curDay.getDayOfMonth(),count,handleType);
            handleDailyCountRepository.save(handleDailyCount);
        }
    }

    /**
     * 更新当月的数量
     * @param handleType
     * @param count
     */
    public void updateMonthCount(HandleType handleType,Integer count){
        LocalDate curDay = LocalDate.now();
        Optional<HandleTotal> handleTotalOptional = handleTotalRepository.findByHandleType(handleType);
        Integer totalCount = handleTotalOptional.get().getTotalCount();
        count += count - totalCount;
        Optional<MonthHandleTotal> monthHandleTotalOptional = monthHandleTotalRepository.findAllByHandleTypeAndYearAndMonth(handleType,curDay.getYear(),curDay.getMonth().getValue());
        if (monthHandleTotalOptional.isPresent()){
            MonthHandleTotal monthHandleTotal = monthHandleTotalOptional.get();
            monthHandleTotal.setTotalCount(count);
            monthHandleTotal.setYear(curDay.getYear());
            monthHandleTotal.setMonth(curDay.getMonthValue());
            monthHandleTotalRepository.save(monthHandleTotal);
        }else {
            MonthHandleTotal handleDailyCount = new MonthHandleTotal(curDay.getYear(),curDay.getMonth().getValue(),handleType,count);
            monthHandleTotalRepository.save(handleDailyCount);
        }
    }

    private void updateTotalCount(HandleType handleType,Integer total){
        handleTotalRepository.updateTotalCount(handleType.name(),total);
    }

    /**
     * 总数量表中进行添加操作
     * @param handleType
     * @param total
     */
    private void addHandleTotal(HandleType handleType,Integer total){
        HandleTotal handleTotal = new HandleTotal(handleType,total);
        handleTotalRepository.save(handleTotal);
    }



    /**
     * 获取对用类型的总数量
     * @param jsonArray
     * @param type
     * @return
     */
    private int handleJsonArray(JSONArray jsonArray,String type){
        int size = jsonArray.size();
        int count = 0;
        for (int i = 0; i < size; i++){
            JSONObject nodeJson = jsonArray.getJSONObject(i);
            count += getCount(nodeJson,type);
        }
        return count;
    }


    /**
     * 统计数量
     * @param jsonObject
     * @param type
     * @return
     */
    private Integer getCount(JSONObject jsonObject,String type){
        Integer count = 0;
        Set<String> keys = jsonObject.keySet();
        for (String key : keys){
            JSONObject countJson = jsonObject.getJSONObject(key);
            count += countJson.getInteger(type);
            if (countJson.containsKey("sub_site")){
                count += handleJsonArray(countJson.getJSONArray("sub_site"),type);
            }
        }
        return count;
    }
}
