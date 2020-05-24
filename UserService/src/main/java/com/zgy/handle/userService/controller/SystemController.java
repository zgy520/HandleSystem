package com.zgy.handle.userService.controller;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class SystemController<T,U> {
    private final SystemService systemRefactorService;

    private String sortedField = "updateTime"; // 排序字段，默认为更新时间

    @Autowired
    public SystemController(SystemService systemRefactorService){
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
    @ApiOperation(value = "新增和编辑信息")
    public ResponseCode<U> update(@Valid @RequestBody U u){
        T t = convertUtoT(u);
        ResponseCode<U> responseCode = systemRefactorService.update(u,t);
        return responseCode;
    }

    /**
     * 获取列表
     * @param pageable
     * @param dto
     * @return
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "分页获取列表")
    public ResponseCode<List<U>> list(@PageableDefault(page = 1,size = 10) Pageable pageable, U dto){
        ResponseCode<List<U>> responseCode = ResponseCode.sucess();
        pageable = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), pageable.getSort());
        Page<T> page = systemRefactorService.findByDynamicQuery(pageable,dto);
        List<T> contentList = page.getContent();
        List<U> dtoList = convertTtoU(contentList);
        responseCode.setPageInfo(page);
        fillList(contentList,dtoList);
        responseCode.setData(dtoList);
        return responseCode;
    }

    /**
     * 获取所有的数据
     * @return
     */
    @GetMapping(value = "all")
    @ApiOperation(value = "获取所有数据")
    public ResponseCode<List<U>> all(){
        ResponseCode<List<U>> responseCode = ResponseCode.sucess();
        List<T> tList = systemRefactorService.findAll();
        List<U> uList = convertTtoU(tList);
        fillList(tList,uList);
        responseCode.setData(uList);
        return responseCode;
    }

    /**
     * 获取到列表后，对列表进行最后的完善
     * @param entityList
     * @param dtoList
     */
    public void fillList(List<T> entityList,List<U> dtoList){}

    /**
     * 根据id获取对象信息
     * @param id
     * @return
     */
    @GetMapping(value = "find/{id}")
    @ApiOperation(value = "根据id获取对应的数据")
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
    @ApiOperation(value = "根据id删除数据")
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
     * 获取下拉框的列表信息
     * 即：id和text
     * @return
     */
    @GetMapping(value = "getSelectList")
    @ApiOperation(value = "获取数据列表,用户下拉框选择时，返回字段未lable和value")
    public ResponseCode<List<SelectDTO>> getSelectList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<T> tList = systemRefactorService.findAll();
        List<SelectDTO> selectDTOS = convertTtoSelectDTOList(tList);
        responseCode.setData(selectDTOS);
        return responseCode;
    }

    /**
     * 将实体列表转化为id和text列表
     * @param tList
     * @return
     */
    public abstract List<SelectDTO> convertTtoSelectDTOList(List<T> tList);

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
