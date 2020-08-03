package com.zgy.handle.userService.controller.base;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.service.base.QueryService;
import com.zgy.handle.userService.service.base.UpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 数据操作相关的controller基类
 * @param <T>
 * @param <U>
 */
@Slf4j
public abstract class UpdateController<T,U> extends BaseController<T> {
    private final UpdateService updateService;
    private final QueryService queryService;
    public UpdateController(UpdateService updateService, QueryService queryService) {
        super(queryService,updateService);
        this.updateService = updateService;
        this.queryService = queryService;
    }

    /**
     * 实体更新
     * 新增
     * 编辑
     * @param u
     * @return
     */
    @PostMapping(value = "update")
    /*@CacheEvict(value = "account",key = "#u.id")*/
    public ResponseCode<U> update(@RequestBody @Valid U u){
        T t = convertUtoT(u);
        ResponseCode<U> responseCode = updateService.update(u,t);
        return responseCode;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "delete/{id}")
    public ResponseCode<U> delete(@PathVariable(value = "id") Long id){
        ResponseCode<U> responseCode = ResponseCode.sucess();
       /* Optional<T> optionalT = systemRefactorService.findById(id);
        ResponseCode<U> responseCode = ResponseCode.sucess();
        if (optionalT.isPresent())
            responseCode.setData(convertTtoU(optionalT.get()));
        else
            throw new EntityNotFoundException("找不到id对应为:{" + id.toString() + "}的值");*/
        queryService.getOne(id);
        updateService.delete(id);
        return responseCode;
    }

    @DeleteMapping(value = "deleteList/{idList}")
    public ResponseCode<String> deleteList(@PathVariable(value = "idList") String idList){
        ResponseCode<String> responseCode = ResponseCode.sucess();
        String[] ids = idList.split(",");
        for (String id : ids){
            updateService.delete(Long.valueOf(id));
        }
        return responseCode;
    }

    /**
     * 将dto转为实体
     * @param u
     * @return
     */
    public abstract T convertUtoT(U u);
}
