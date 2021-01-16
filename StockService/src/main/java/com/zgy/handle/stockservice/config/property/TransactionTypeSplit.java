package com.zgy.handle.stockservice.config.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: a4423
 * @date: 2021/1/6 21:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "transactiontype")
public class TransactionTypeSplit {
    /**
     * 大单的分界线
     */
    private Integer bigCount;
    /**
     * 中单的分界线
     */
    private Integer mediuCount;
    /**
     * 小单的分界线
     */
    private Integer smallCount;
}
