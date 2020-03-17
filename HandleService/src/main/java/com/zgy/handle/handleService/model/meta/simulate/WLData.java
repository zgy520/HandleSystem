package com.zgy.handle.handleService.model.meta.simulate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class WLData {
    private Long userId;
    private String orderNum; // 订单号
    private String startLocation; // 开始位置
    private String endLocation; // 目的地
    private String person; // 联系人
    private String phoneNumber; // 联系方式
}
