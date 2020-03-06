package com.zgy.handle.knowledge.service;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.common.zuul.context.UserContext;
import com.zgy.handle.knowledge.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public abstract class KnowledgeService<T,U> {
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    public KnowledgeService(KnowledgeRepository knowledgeRepository){
        this.knowledgeRepository = knowledgeRepository;
    }


    public String getPostName(){
        return request.getHeader(UserContext.POST_NAME);
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
        return knowledgeRepository.findAll();
    }

    public Optional<T> findById(Long id){
        return knowledgeRepository.findById(id);
    }


    /**
     * 根据分页获取数据
     * @param pageable
     * @return
     */
    public Page<T> findAll(Pageable pageable){
        return knowledgeRepository.findAll(pageable);
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
            t = (T) knowledgeRepository.save(t);
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
                    knowledgeRepository.deleteById(id);
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
}
