package com.zgy.handle.userservice.repository.menu;

import com.zgy.handle.userservice.model.menu.Menu;
import com.zgy.handle.userservice.repository.base.QueryRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Query(nativeQuery = true,value = "select 1 from system_menu where (name = :name or code = :code) and id != :id limit 1")
    Integer countByNameOrCodeAndIdNot(@Param("name") String name,@Param("code") String code,@Param("id") String id);

    /**
     * 根据菜单的ID列表获取所有的菜单
     * @param menuIdList
     * @return
     */
    List<Menu> findByIdIn(List<Long> menuIdList);
}
