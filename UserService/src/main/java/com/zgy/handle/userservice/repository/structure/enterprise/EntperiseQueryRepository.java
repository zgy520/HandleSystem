package com.zgy.handle.userservice.repository.structure.enterprise;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.userservice.model.structure.Enterprise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author a4423
 */
@Repository
public interface EntperiseQueryRepository extends QueryRepository<Enterprise> {
    /**
     * 新增时得唯一性检查
     *
     * @param name
     * @param code
     * @return
     */
    @Query(nativeQuery = true, value = "select 1 from system_enterprise where name = :name or code = :code limit 1")
    Integer countByCodeOrName(@Param("name") String name, @Param("code") String code);

    /**
     * 编辑时得唯一性检查
     *
     * @param name
     * @param code
     * @param id
     * @return
     */
    @Query(nativeQuery = true, value = "select 1 from system_enterprise where (name = :name or code = :code) and id != :id limit 1")
    Integer countByCodeOrNameAndIdNot(@Param("name") String name, @Param("code") String code, @Param("id") Long id);
}
