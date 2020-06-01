package com.zgy.handle.handleService.service;

import com.mysql.jdbc.SQLError;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.common.zuul.context.UserContext;
import com.zgy.handle.handleService.repository.SystemRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class SystemService<T,U> {
    private SystemRepository systemRepository;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    public SystemService(SystemRepository systemRepository){
        this.systemRepository = systemRepository;
    }

    /**
     * 获取当前用户的企业id
     * @return
     */
    public String getEnterpriseId(){
        return request.getHeader(UserContext.ENTERPRISE_ID);
    }

    /**
     * 获取当前用户的部门id
     * @return
     */
    public String getDepartId(){
        return request.getHeader(UserContext.ORG_ID);
    }

    /**
     * 获取用户的个人id
     * @return
     */
    public String getPersonalId(){
        return request.getHeader(UserContext.USER_ID);
    }

    /**
     * 获取所有的数据
     * @return
     */
    public List<T> findAll(){
        return systemRepository.findAll();
    }

    public Optional<T> findById(Long id){
        return systemRepository.findById(id);
    }


    /**
     * 根据分页获取数据
     * @param pageable
     * @return
     */
    public Page<T> findAll(Pageable pageable){
        return systemRepository.findAll(pageable);
    }

    /**
     * 动态查询
     * 子类需要重载改方法，否则将返回findAll的查询结果
     * @param pageable
     * @param dto
     * @return
     */
    public Page<T> findByDynamicQuery(Pageable pageable,U dto){
        return this.findAll(pageable);
    }

    /**
     * 唯一性检查
     * 返回true表明重复，false不重复，为默认值
     * @param u
     * @param t
     * @return
     */
    public boolean checkUnique(U u, T t){
        return false;
    }

    @Transactional
    public ResponseCode<T> update(U u, T t){
        ResponseCode<T> responseCode = ResponseCode.sucess();
        if (checkUnique(u,t)){
            responseCode.setSuccess(false);
            responseCode.setMsg("违反了唯一性原则!");
        }else {
            beforeUpdate(u,t);
            t = (T) systemRepository.save(t);
            postUpdate(t,u);
            responseCode.setData(t);
        }
        return responseCode;
    }
    public void beforeUpdate(U u,T t){}

    public void postUpdate(T t,U u){}

    public ResponseCode<T> delete(Long id){
        ResponseCode<T> responseCode = ResponseCode.sucess();
        if (id == null){
            responseCode.setSuccess(false);
            responseCode.setMsg("删除的id不能为空!");
        }else {
            Optional<T>  optionalT = this.findById(id);
            if (optionalT.isPresent()){
                responseCode.setData(optionalT.get());
                try {
                    systemRepository.deleteById(id);
                } catch (Exception exception){
                    throw new ConstraintViolationException("有关联数据，不能删除!",null,"");
                }
            }else {
                responseCode.setSuccess(false);
                responseCode.setMsg("不存在id为:{" + id.toString() + "的数据");
            }
        }

        return responseCode;
    }
}
