package com.zgy.handle.stockservice.service.detail;

import com.zgy.handle.stockservice.config.property.TransactionTypeSplit;
import com.zgy.handle.stockservice.dao.detail.StockDailyDetailRepository;
import com.zgy.handle.stockservice.dto.detail.BaseDetailDTO;
import com.zgy.handle.stockservice.dto.detail.TransactionTypeDTO;
import com.zgy.handle.stockservice.model.detail.StockDailyDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

    private List<TransactionTypeDTO> getTransactionType(List<StockDailyDetail> detailList){
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
