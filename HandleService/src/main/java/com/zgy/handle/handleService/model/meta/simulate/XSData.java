package com.zgy.handle.handleService.model.meta.simulate;

import lombok.Data;

/**
 * 销售得数据
 */
@Data
public class XSData {
    private String saleCustomer; //销售客户
    private String salePerson; //销售人
     private String saleDate; //销售时间
    private String saleMoney; //销售金额
    private String saleCount; //销售数量
}
