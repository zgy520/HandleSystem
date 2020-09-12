package com.zgy.handle.userservice.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 唯一性判断信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniqueInfo {
    private boolean result = false; // 唯一性检查的结果
    private String msg; // 返回的消息

    public static UniqueInfo getUniqueInfo(String msg){
        UniqueInfo uniqueInfo = new UniqueInfo(true,msg);
        return uniqueInfo;
    }

    public static UniqueInfo getDefaultUnique(){
        UniqueInfo uniqueInfo = new UniqueInfo(false,"");
        return uniqueInfo;
    }
}
