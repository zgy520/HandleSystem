package com.zgy.excel.utils;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author: a4423
 * @date: 2020/9/16 6:15
 */
public class ExcelValidateHelper {
    public static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 验证实体对象
     * @param obj
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     */
    public static <T> String validateEntity(T obj) throws NoSuchFieldException {
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && !set.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : set) {
                Field declaredField = obj.getClass().getDeclaredField(constraintViolation.getPropertyPath().toString());
                /*ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
                //拼接错误信息，包含当前出错数据的标题名字+错误信息
                result.append(annotation.value()[0]).append(constraintViolation.getMessage()).append(";");*/
                stringBuilder.append(declaredField.getName()).append(constraintViolation.getMessage()).append(";");
            }
        }
        return stringBuilder.toString();
    }
}
