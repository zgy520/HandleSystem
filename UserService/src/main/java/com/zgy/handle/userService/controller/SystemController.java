package com.zgy.handle.userService.controller;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.service.SystemRefactorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public abstract class SystemController<T,U> {
    private final SystemRefactorService systemRefactorService;

    private String sortedField = "updateTime"; // 排序字段，默认为更新时间

    @Autowired
    public SystemController(SystemRefactorService systemRefactorService){
        this.systemRefactorService = systemRefactorService;
    }

    /**
     * 实体更新
     * 新增
     * 编辑
     * @param u
     * @return
     */
    @PostMapping(value = "update")
    public ResponseCode<U> update(@RequestBody U u){
        ResponseCode<U> responseCode = ResponseCode.sucess();
        log.info("获取到的数据为:" + u);
        T t = convertUtoT(u);
        systemRefactorService.update(u,t);
        return responseCode;
    }

    /**
     * 获取列表
     * @param pageable
     * @param dto
     * @return
     */
    @GetMapping(value = "list")
    public ResponseCode<List<U>> getRoleList(@PageableDefault(page = 1,size = 10) Pageable pageable, U dto){
        ResponseCode<List<U>> responseCode = ResponseCode.sucess();
        pageable = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), Sort.Direction.DESC,getSortedField());
        Page<T> page = systemRefactorService.findByDynamicQuery(pageable,dto);
        List<T> contentList = page.getContent();
        List<U> dtoList = convertTtoU(contentList);
        responseCode.setData(dtoList);
        return responseCode;
    }

    /**
     * 根据id获取对象信息
     * @param id
     * @return
     */
    @GetMapping(value = "find/{id}")
    public ResponseCode<U> findById(@PathVariable(value = "id") Long id){
        ResponseCode<U> responseCode = ResponseCode.sucess();
        Optional<T> optionalT = systemRefactorService.findById(id);
        if (!optionalT.isPresent()){
            throw new EntityNotFoundException("实体中对应id={" + id + "}的数据不存在");
        }
        responseCode.setData(convertTtoU(optionalT.get()));
        return responseCode;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "delete/{id}")
    public ResponseCode<U> delete(@PathVariable(value = "id") Long id){
        Optional<T> optionalT = systemRefactorService.findById(id);
        ResponseCode<U> responseCode = ResponseCode.sucess();
        if (optionalT.isPresent())
            responseCode.setData(convertTtoU(optionalT.get()));
        else
            throw new EntityNotFoundException("找不到id对应为:{" + id.toString() + "}的值");
        systemRefactorService.delete(id);
        return responseCode;
    }

    /**
     * 实体列表转化为dto列表
     * @param tList 实体列表
     * @return  dto列表
     */
    public abstract List<U> convertTtoU(List<T> tList);

    /**
     * 将实体对象转为dto
     * @param t
     * @return
     */
    public abstract U convertTtoU(T t);

    /**
     * 将dto转为实体
     * @param u
     * @return
     */
    public abstract T convertUtoT(U u);


    /**
     * 设置排序字段，默认为updateTime
     * 可根据需要进行重载
     * @return
     */
    public void setSortedField(String sortedField){
        this.sortedField = sortedField;
    }

    public String getSortedField(){
        return this.sortedField;
    }




}
