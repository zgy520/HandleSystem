package com.zgy.handle.common.controller.base;

import com.zgy.handle.common.model.common.SelectDTO;
import com.zgy.handle.common.model.page.PageInfo;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.common.service.base.UpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * 查询相关的controller基类
 * @author a4423
 * @param <T>
 * @param <U>
 */
@Slf4j
public abstract class BaseQueryController<T,U> extends BaseController<T> {
    private final UpdateService updateService;
    private final QueryService queryService;
    public BaseQueryController(UpdateService updateService, QueryService queryService) {
        super(queryService,updateService);
        this.updateService = updateService;
        this.queryService = queryService;
    }

    /**
     * 获取列表
     * @param pageable
     * @param dto
     * @return
     */
    @GetMapping(value = "list")
    public ResponseCode<List<U>> list(Pageable pageable, U dto){
        ResponseCode<List<U>> responseCode = ResponseCode.sucess();
        pageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        Page<T> page = queryService.findByDynamicQuery(pageable,dto);
        List<T> contentList = page.getContent();
        List<U> dtoList = convertTtoU(contentList);
        responseCode.setPageInfo(page);
        fillList(contentList,dtoList);
        responseCode.setData(dtoList);
        return responseCode;
    }

    /**
     * 获取列表
     * @param pageInfo
     * @param dto
     * @return
     */
    @GetMapping(value = "page")
    public ResponseCode<List<U>> list(PageInfo pageInfo, U dto){
        ResponseCode<List<U>> responseCode = ResponseCode.sucess();
        if (pageInfo.getCurrent() == null){
            // 默认第一页
            pageInfo.setCurrent(1);
        }
        if (pageInfo.getPageSize() == null){
            // 默认10条
            pageInfo.setPageSize(10);
        }
        Pageable pageable = pageable = PageRequest.of(pageInfo.getCurrent() - 1, pageInfo.getPageSize());
        if (pageable.getSort()== Sort.unsorted()){
            pageable = PageRequest.of(pageInfo.getCurrent() - 1, pageInfo.getPageSize(),Sort.by(Sort.Direction.DESC,"createTime"));
        }
        Page<T> page = queryService.findByDynamicQuery(pageable,dto);
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
    public ResponseCode<List<U>> all(){
        ResponseCode<List<U>> responseCode = ResponseCode.sucess();
        List<T> tList = queryService.findAll();
        List<U> uList = convertTtoU(tList);
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
    public ResponseCode<U> findById(@PathVariable(value = "id") Long id){
        ResponseCode<U> responseCode = ResponseCode.sucess();
        Optional<T> optionalT = queryService.findById(id);
        if (!optionalT.isPresent()){
            throw new EntityNotFoundException("实体中对应id={" + id + "}的数据不存在");
        }
        responseCode.setData(convertTtoU(optionalT.get()));
        return responseCode;
    }

    /**
     * 获取下拉框的列表信息
     * 即：id和text
     * @return
     */
    @GetMapping(value = "getSelectList")
    public ResponseCode<List<SelectDTO>> getSelectList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<T> tList = queryService.findAll();
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
}
