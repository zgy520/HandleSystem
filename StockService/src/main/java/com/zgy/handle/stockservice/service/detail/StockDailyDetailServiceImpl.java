package com.zgy.handle.stockservice.service.detail;

import cn.hutool.core.date.StopWatch;
import com.zgy.handle.stockservice.config.property.TransactionTypeSplit;
import com.zgy.handle.stockservice.constant.FixTimeConstant;
import com.zgy.handle.stockservice.dao.StockDailyRepository;
import com.zgy.handle.stockservice.dao.detail.StockDailyDetailRepository;
import com.zgy.handle.stockservice.dto.detail.BaseDetailDTO;
import com.zgy.handle.stockservice.dto.detail.ContinueInfo;
import com.zgy.handle.stockservice.dto.detail.TransactionTypeDTO;
import com.zgy.handle.stockservice.dto.detail.bidding.BiddingDTO;
import com.zgy.handle.stockservice.dto.detail.test.StockDailyBase;
import com.zgy.handle.stockservice.dto.detail.test.StockDailyInterfaceBase;
import com.zgy.handle.stockservice.model.StockDaily;
import com.zgy.handle.stockservice.model.detail.StockDailyDetail;
import com.zgy.handle.stockservice.service.util.StockUtilService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: a4423
 * @date: 2021/1/2 10:37
 */
@Slf4j
@Service
public class StockDailyDetailServiceImpl implements StockDailyDetailService {

    @Autowired
    private TransactionTypeSplit tansactionTypeSplit;
    @Autowired
    private StockUtilService stockUtilService;
    @Autowired
    private StockDailyRepository stockDailyRepository;

    private StockDailyDetailRepository stockDailyDetailRepository;

    @Autowired
    public StockDailyDetailServiceImpl(StockDailyDetailRepository stockDailyDetailRepository) {
        this.stockDailyDetailRepository = stockDailyDetailRepository;
    }

    /**
     * 基本信息
     */
    @Override
    public List<BaseDetailDTO> base(String code, LocalDate localDate) {
        List<BaseDetailDTO> baseDetailDTOList = new ArrayList<>();
        List<StockDailyDetail> list = stockDailyDetailRepository.findByCodeAndCurDateGreaterThanEqual(code, localDate);
        Map<LocalDate, List<StockDailyDetail>> dateListMap = list.stream()
                .collect(Collectors.groupingBy(StockDailyDetail::getCurDate));
        dateListMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(item -> {

            List<StockDailyDetail> detailList = item.getValue();
            BigDecimal infFlowSum = sum(detailList, 1);
            BigDecimal outFlowSum = sum(detailList, -1);
            BigDecimal flow = infFlowSum.subtract(outFlowSum);
            log.info("日期:" + item.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "总的交易次数为:" + detailList.size() + ":" + count(detailList, 0) + ",买入次数为:" + detailList.stream().filter(detail -> detail.getType() == 1).count() + ":" + count(detailList, 1)
                    + "买入金额为:" + infFlowSum + "，卖出次数为:" + detailList.stream().filter(detail -> detail.getType() == -1).count() + ":" + count(detailList, -1) + "卖出金额为:" + outFlowSum
                    + "，净流入:" + flow);
            BaseDetailDTO baseDetailDTO = new BaseDetailDTO(item.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), detailList.size(), count(detailList, 0), detailList.stream().filter(detail -> detail.getType() == 1).count(), count(detailList, 1),
                    detailList.stream().filter(detail -> detail.getType() == -1).count(), count(detailList, -1), infFlowSum.doubleValue(), outFlowSum.doubleValue(), flow.doubleValue());
            baseDetailDTOList.add(baseDetailDTO);
        });
        return baseDetailDTOList;
    }

    /**
     * 交易类型信息
     *
     * @param code      代码
     * @param localDate 交易日期
     * @return
     */
    @Override
    public List<TransactionTypeDTO> transactionInfo(String code, LocalDate localDate) {
        List<TransactionTypeDTO> transactionTypeDTOList = new ArrayList<>();
        log.info(tansactionTypeSplit.getBigCount() + ":" + tansactionTypeSplit.getMediuCount() + ":" + tansactionTypeSplit.getSmallCount());

        List<StockDailyDetail> list = stockDailyDetailRepository.findByCodeAndCurDateGreaterThanEqual(code, localDate);

        Map<LocalDate, List<StockDailyDetail>> dateListMap = list.stream()
                .collect(Collectors.groupingBy(StockDailyDetail::getCurDate));
        dateListMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(item -> {
            List<StockDailyDetail> detailList = item.getValue();
            List<TransactionTypeDTO> typeList = getTransactionType(detailList);
            typeList.forEach(type -> {
                type.setCode(code);
                type.setTradingDate(item.getKey().toString());
            });
            transactionTypeDTOList.addAll(typeList);
        });

        var result = transactionTypeDTOList.stream().sorted(Comparator.comparing(TransactionTypeDTO::getType).thenComparing(TransactionTypeDTO::getTradingDate))
                .collect(Collectors.toList());

        result.forEach(item -> {
            log.info(item.toString());
        });

        return result;
    }

    /**
     * 分析某天的交易情况
     *
     * @param localDate 分析日期
     */
    @Override
    public void dailyAnalysis(String code, LocalDate localDate) {
        if (localDate == null) {
            localDate = LocalDate.now();
        }
        if (StringUtils.isEmpty(code)) {
            code = "601100";
        }
        List<StockDailyDetail> list = stockDailyDetailRepository.findByCodeAndCurDate(code, localDate)
                .stream().sorted(Comparator.comparing(StockDailyDetail::getCurTime))
                .collect(Collectors.toList());
        // 持续买入情况
        continueBuy(list);
    }

    /**
     * 竞价分析
     *
     * @param code      股票代码
     * @param localDate 日期
     */
    @Override
    public void dailyBiddingAnalysis(String code, LocalDate localDate) {
        List<LocalDate> dealDateList = stockUtilService.getDealDateAndSortDesc(code);
        List<StockDaily> stockDailyList = stockDailyRepository.findByCodeAndCurDateIn(code,dealDateList);
        List<BiddingDTO> biddingList = new ArrayList<>();

        dealDateList.forEach(dealDate -> {
            List<StockDailyDetail> list = stockDailyDetailRepository.findByCodeAndCurDateAndCurTimeLessThan(code, dealDate, FixTimeConstant.getBiddingTime())
                    .stream().sorted(Comparator.comparing(StockDailyDetail::getCurTime))
                    .collect(Collectors.toList());
            Optional<StockDaily> stockDailyOptional = stockDailyList.stream().filter(stockDaily -> stockDaily.getCurDate().equals(dealDate)).findFirst();
            Double zdf = 0d;
            if (stockDailyOptional.isPresent()){
             zdf = stockDailyOptional.get().getZdf() * 100;
            }
            biddingList.add(new BiddingDTO(dealDate,list.size(),zdf));
        });
        biddingList.forEach(biddingDTO -> log.info("交易日期:" + biddingDTO.getDealDate() + ":" + biddingDTO.getCount() + ":" + biddingDTO.getZdf()));

    }

    /**
     * 性能测试
     */
    @Override
    public void performanceTest() {
        String code = "601100";
        log.info("获取所有数据");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<StockDailyDetail> list = stockDailyDetailRepository.findAllByCode(code);
        stopWatch.stop();
        log.info("获取实体所需的时间为:" + stopWatch.getTotalTimeMillis() + "毫秒");
        log.info("基于接口获取所有数据");
        StopWatch interfaceWatch = new StopWatch();
        interfaceWatch.start();
        List<StockDailyBase> baseList = stockDailyDetailRepository.findByXCode(code);
        interfaceWatch.stop();
        log.info("基于dto获取所有数据的时间为:" + interfaceWatch.getTotalTimeMillis() + "毫秒");
        StopWatch lastWatch = new StopWatch();
        lastWatch.start();
        List<StockDailyInterfaceBase> interfaceList = stockDailyDetailRepository.findByCode(code);
        lastWatch.stop();
        log.info("基于接口获取所有数据的时间为:" + lastWatch.getTotalTimeMillis() + "毫秒");

    }

    private void continueBuy(List<StockDailyDetail> list) {
        List<ContinueInfo> continueInfoList = new ArrayList<>();
        int count = 0;
        int total = 0;
        LocalTime startTime = null;
        LocalTime endTime = null;
        for (int i = 0; i < list.size(); i++) {
            StockDailyDetail detail = list.get(i);
            if (detail.getResult() != -1) {
                // 买入
                count++;
                if (startTime == null) {
                    startTime = detail.getCurTime();
                }
                total += detail.getTradingCount();
            } else {
                // 相反发展
                if (i > 0) {
                    endTime = list.get(i - 1).getCurTime();
                }

                if (total > 200) {
                    ContinueInfo continueInfo = new ContinueInfo();
                    continueInfo.setCount(count);
                    continueInfo.setStartTime(startTime);
                    continueInfo.setEndTime(endTime);
                    continueInfo.setType("买入");
                    continueInfo.setTotal(total);
                    continueInfoList.add(continueInfo);
                }
                count = 0;
                total = 0;
                startTime = null;
                endTime = null;
            }
        }
        log.info("连续超过10次买入的数量为:" + continueInfoList.size());
        continueInfoList.stream().forEach(continueInfo -> {
            log.info(continueInfo.toString());
        });
    }

    private List<TransactionTypeDTO> getTransactionType(List<StockDailyDetail> detailList) {
        List<TransactionTypeDTO> list = new ArrayList<>();
        /**
         * 小散， 10收一下的
         */
        TransactionTypeDTO smallDTO = new TransactionTypeDTO();
        smallDTO.setType("小散");
        smallDTO.setTradingCount(detailList.stream().filter(detail -> detail.getTradingCount() <= tansactionTypeSplit.getSmallCount()).count());
        smallDTO.setTradingVolume(detailList.stream().filter(detail -> detail.getTradingCount() <= tansactionTypeSplit.getSmallCount()).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));

        smallDTO.setBuyCount(detailList.stream().filter(detail -> detail.getTradingCount() <= tansactionTypeSplit.getSmallCount() && detail.getType() == 1).count());
        smallDTO.setBuyVolume(detailList.stream().filter(detail -> detail.getTradingCount() <= tansactionTypeSplit.getSmallCount() && detail.getType() == 1).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));

        smallDTO.setSaleCount(detailList.stream().filter(detail -> detail.getTradingCount() <= tansactionTypeSplit.getSmallCount() && detail.getType() == -1).count());
        smallDTO.setSaleVolume(detailList.stream().filter(detail -> detail.getTradingCount() <= tansactionTypeSplit.getSmallCount() && detail.getType() == -1).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));

        /**
         * 中散， 10到40之间
         */
        TransactionTypeDTO smallMediuDTO = new TransactionTypeDTO();
        smallMediuDTO.setType("中等散户");
        smallMediuDTO.setTradingCount(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getSmallCount() && detail.getTradingCount() <= tansactionTypeSplit.getMediuCount()).count());
        smallMediuDTO.setTradingVolume(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getSmallCount() && detail.getTradingCount() <= tansactionTypeSplit.getMediuCount()).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));

        smallMediuDTO.setBuyCount(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getSmallCount() && detail.getTradingCount() <= tansactionTypeSplit.getMediuCount() && detail.getType() == 1).count());
        smallMediuDTO.setBuyVolume(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getSmallCount() && detail.getTradingCount() <= tansactionTypeSplit.getMediuCount() && detail.getType() == 1).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));

        smallMediuDTO.setSaleCount(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getSmallCount() && detail.getTradingCount() <= tansactionTypeSplit.getMediuCount() && detail.getType() == -1).count());
        smallMediuDTO.setSaleVolume(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getSmallCount() && detail.getTradingCount() <= tansactionTypeSplit.getMediuCount() && detail.getType() == -1).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));

        /**
         * 机构 40 到100 之间
         */
        TransactionTypeDTO jgDTO = new TransactionTypeDTO();
        jgDTO.setType("机构");
        jgDTO.setTradingCount(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getMediuCount() && detail.getTradingCount() < tansactionTypeSplit.getBigCount()).count());
        jgDTO.setTradingVolume(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getMediuCount() && detail.getTradingCount() < tansactionTypeSplit.getBigCount()).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));

        jgDTO.setBuyCount(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getMediuCount() && detail.getTradingCount() < tansactionTypeSplit.getBigCount() && detail.getType() == 1).count());
        jgDTO.setBuyVolume(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getMediuCount() && detail.getTradingCount() < tansactionTypeSplit.getBigCount() && detail.getType() == 1).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));

        jgDTO.setSaleCount(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getMediuCount() && detail.getTradingCount() < tansactionTypeSplit.getBigCount() && detail.getType() == -1).count());
        jgDTO.setSaleVolume(detailList.stream().filter(detail -> detail.getTradingCount() > tansactionTypeSplit.getMediuCount() && detail.getTradingCount() < tansactionTypeSplit.getBigCount() && detail.getType() == -1).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));


        /**
         * 控盘者： 100 以上
         */
        TransactionTypeDTO bigerDTO = new TransactionTypeDTO();
        bigerDTO.setType("主力");
        bigerDTO.setTradingCount(detailList.stream().filter(detail -> detail.getTradingCount() >= tansactionTypeSplit.getBigCount()).count());
        bigerDTO.setTradingVolume(detailList.stream().filter(detail -> detail.getTradingCount() >= tansactionTypeSplit.getBigCount()).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));

        bigerDTO.setBuyCount(detailList.stream().filter(detail -> detail.getTradingCount() >= tansactionTypeSplit.getBigCount() && detail.getType() == 1).count());
        bigerDTO.setBuyVolume(detailList.stream().filter(detail -> detail.getTradingCount() >= tansactionTypeSplit.getBigCount() && detail.getType() == 1).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));

        bigerDTO.setSaleCount(detailList.stream().filter(detail -> detail.getTradingCount() >= tansactionTypeSplit.getBigCount() && detail.getType() == -1).count());
        bigerDTO.setSaleVolume(detailList.stream().filter(detail -> detail.getTradingCount() >= tansactionTypeSplit.getBigCount() && detail.getType() == -1).collect(Collectors.summingInt(StockDailyDetail::getTradingCount)));


        list.add(smallDTO);
        list.add(bigerDTO);
        list.add(smallMediuDTO);
        list.add(jgDTO);
        return list;
    }

    private Integer count(List<StockDailyDetail> detailList, Integer type) {
        if (type != 0) {
            detailList = detailList.stream().filter(detail -> detail.getType().equals(type))
                    .collect(Collectors.toList());
        }
        return detailList.stream().mapToInt(StockDailyDetail::getTradingCount).sum();
    }

    /**
     * 计算总金额
     *
     * @param detailList
     * @return
     */
    private BigDecimal sum(List<StockDailyDetail> detailList, Integer type) {
        detailList = detailList.stream().filter(detail -> detail.getType().equals(type)).collect(Collectors.toList());
        BigDecimal sum = BigDecimal.ZERO;
        for (StockDailyDetail stockDailyDetail : detailList) {
            sum = sum.add(new BigDecimal(stockDailyDetail.getPrice() * stockDailyDetail.getTradingCount() * 100));
        }
        return sum.divide(new BigDecimal(100000000)).setScale(2, RoundingMode.HALF_UP);
    }


}
