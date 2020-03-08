package com.zgy.handle.userService.util.Str;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StrUtils {
    /**
     * 例子： 将string List转化为 long List
     * @param list
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T,U> List<U> transformList(List<T> list, Function<T,U> function){
        return list.stream().map(function).collect(Collectors.toList());
    }
    public static <T,U> Set<U> transformSet(List<T> list, Function<T,U> function){
        return list.stream().map(function).collect(Collectors.toSet());
    }
}
