package com.zgy.handle.userservice.repository.menu;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.userservice.model.menu.Button;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a4423
 */
@Repository
public interface ButtonQueryRepository extends QueryRepository<Button> {

    List<Button> findByIdIn(List<Long> buttonIdList);

    /**
     * 是否存在相同得编码或名称
     *
     * @param name
     * @param code
     * @return
     */
    @Query(nativeQuery = true, value = "select 1 from system_button where name = :name or code = :code limit 1")
    Integer countByNameOrCode(@Param("name") String name, @Param("code") String code);

    /**
     * 编辑是是否存在重复得信息
     *
     * @param name
     * @param code
     * @param id
     * @return
     */
    @Query(nativeQuery = true, value = "select 1 from system_button where (name = :name or code = :code) and id != :id limit 1")
    Integer countByNameOrCodeAndIdNot(@Param("name") String name, @Param("code") String code, @Param("id") String id);
}