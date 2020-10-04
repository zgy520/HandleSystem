package com.zgy.handle.userservice.service.authority.post.query;

import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.PostDTO;
import com.zgy.handle.userservice.model.common.TransferDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a4423
 */
@Repository
public interface PostQueryService extends QueryService<Post, PostDTO> {
    /**
     * 根据角色ID获取所有的账户信息
     *
     * @param postId
     * @return
     */
    List<TransferDTO> getAccountListByPostId(Long postId);
}
