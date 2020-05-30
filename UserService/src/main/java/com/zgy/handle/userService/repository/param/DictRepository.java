package com.zgy.handle.userService.repository.param;

import com.zgy.handle.userService.model.parameter.Dict;
import com.zgy.handle.userService.model.parameter.DictType;
import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DictRepository extends SystemRepository<Dict>, JpaSpecificationExecutor<Dict> {

    /**
     * 根据code获取数量
     * @param code
     * @return
     */
    @Query(nativeQuery=true,value="select count(*) from system_dict where code = ?1 and id != ?2")
    Integer countByCode(String code,Long id);
    @Query(nativeQuery=true,value="select count(*) from system_dict where code = ?1")
    Integer countByCode(String code);

    Optional<Dict> findByCode(String code);
    List<Dict> findByParentId(Long parentId);

    static Specification<Dict> filterDictType(DictType dictType){
        return (root,query,builder) -> builder.equal(root.get("dictType"),dictType);
    }
}
