package com.zgy.handle.userService.service.base.impl;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.model.BaseModel;
import com.zgy.handle.userService.model.common.UniqueInfo;
import com.zgy.handle.userService.repository.base.UpdateRepository;
import com.zgy.handle.userService.service.base.UpdateService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public abstract class UpdateServiceImpl<T,U> extends BaseServiceImpl<T> implements UpdateService<T,U> {

    private final UpdateRepository updateRepository;
    public UpdateServiceImpl(UpdateRepository updateRepository) {
        super(updateRepository);
        this.updateRepository = updateRepository;
    }


    @Override
    public ResponseCode<T> update(U u, T t) {
        ResponseCode<T> responseCode = ResponseCode.sucess();
        UniqueInfo uniqueInfo = checkUnique(u,t);
        if (uniqueInfo.isResult()){
            responseCode.setSuccess(false);
            responseCode.setMsg(uniqueInfo.getMsg());
        }else {
            beforeUpdate(u,t);
            fillPartFields(t);

            t = (T) updateRepository.save(t);
            postUpdate(t,u);
            responseCode.setData(t);
        }
        return responseCode;
    }

    @Override
    public ResponseCode<T> delete(Long id) {
        ResponseCode<T> responseCode = ResponseCode.sucess();
        if (id == null){
            responseCode.setSuccess(false);
            responseCode.setMsg("删除的id不能为空!");
        }else {
            Optional<T> optionalT = updateRepository.findById(id);
            if (optionalT.isPresent()){
                responseCode.setData(optionalT.get());
                try {
                    updateRepository.deleteById(id);
                }catch (Exception ex){
                    responseCode.setSuccess(false);
                    responseCode.setMsg(ex.getMessage());
                }
            }else {
                responseCode.setSuccess(false);
                responseCode.setMsg("不存在id为:{" + id.toString() + "的数据");
            }
        }

        return responseCode;
    }

    @Override
    public UniqueInfo checkUnique(U u, T t) {
        return UniqueInfo.getDefaultUnique();
    }

    @Override
    public void beforeUpdate(U u, T t) {

    }

    @Override
    public void postUpdate(T t, U u) {

    }

    /**
     * 填充部分字段
     * 包括创建者id和所属者id
     * @param t
     */
    private void fillPartFields(T t){
        if (t instanceof BaseModel){
            ((BaseModel) t).setCreatedId(getPersonalId());
            ((BaseModel) t).setBelongId(getDepartId());
        }
    }
}