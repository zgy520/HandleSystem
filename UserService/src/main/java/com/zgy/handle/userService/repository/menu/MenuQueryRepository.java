package com.zgy.handle.userService.repository.menu;

import com.zgy.handle.userService.model.menu.Menu;
import com.zgy.handle.userService.repository.base.QueryRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuQueryRepository extends QueryRepository<Menu> {
    /**
     * 新增时检查唯一性
     * @param name
     * @param code
     * @return
     */
    @Query(nativeQuery = true,value = "select 1 from system_menu where name = :name or code = :code limit 1")
    Integer countByMenuNameOrCode(@Param("name") String name,@Param("code") String code);

    /**
     * 编辑时进行唯一性得检查
     * @param name
     * @param code
     * @param id
     * @return
     */
    @Query(nativeQuery = true,value = "select 1 from system_menu where (name = :name or code = :code) and id = :id limit 1")
    Integer countByNameOrCodeAndIdNot(@Param("name") String name,@Param("code") String code,@Param("id") String id);
}
