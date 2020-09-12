package com.zgy.handle.userservice.repository.param;

import com.zgy.handle.userservice.model.parameter.Param;
import com.zgy.handle.userservice.model.parameter.ParamType;
import com.zgy.handle.userservice.repository.SystemRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamRepository extends SystemRepository<Param>, JpaSpecificationExecutor<Param> {

    @Query(nativeQuery = true,value = "select  count(*) from system_param where code = ?1")
    Integer countByCode(String code);

    @Query(nativeQuery = true,value = "select  count(*) from system_param where code = ?1 and id != ?2")
    Integer countByCode(String code,Long id);



    /**
     * 根据参数类型过滤
     * @param paramType
     * @return
     */
    static Specification<Param> filterParamType(ParamType paramType){
        return (root,query,builder) -> builder.equal(root.get("paramType"),paramType);
    }

    /**
     * 根据可见性进行过滤
     * @return
     */
    static Specification<Param> visibleParam(){
        return (root,query,builder) -> builder.equal(root.get("visible"),true);
    }

}
