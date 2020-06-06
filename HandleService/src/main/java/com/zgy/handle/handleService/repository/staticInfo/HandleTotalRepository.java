package com.zgy.handle.handleService.repository.staticInfo;

import com.zgy.handle.handleService.model.staticInfo.HandleTotal;
import com.zgy.handle.handleService.model.staticInfo.HandleType;
import com.zgy.handle.handleService.repository.SystemRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface HandleTotalRepository extends SystemRepository<HandleTotal> {
    Optional<HandleTotal> findByHandleType(HandleType handleType);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update handle_static_total set totalCount = ?2 where handleType = ?1")
    void updateTotalCount(String handleType,Integer count);
}
