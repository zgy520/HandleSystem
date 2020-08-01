package com.zgy.handle.userService.repository.structure.enterprise;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.repository.base.QueryRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntperiseQueryRepository extends QueryRepository<Enterprise> {
    /**
     * 新增时得唯一性检查
     * @param name
     * @param code
     * @return
     */
    @Query(nativeQuery = true,value = "select 1 from system_enterprise where name = :name or code = :code limit 1")
    Integer countByCodeOrName(@Param("name") String name, @Param("code") String code);

    /**
     * 编辑时得唯一性检查
     * @param name
     * @param code
     * @param id
     * @return
     */
    @Query(nativeQuery = true,value = "select 1 from system_enterprise where (name = :name or code = :code) and id = :id limit 1")
    Integer countByCodeOrNameAndIdNot(@Param("name") String name, @Param("code") String code,@Param("id") Long id);
}
