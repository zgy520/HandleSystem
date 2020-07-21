package com.zgy.handle.userService.service.authority.post.query;

import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.PostDTO;
import com.zgy.handle.userService.model.authority.role.Role;
import com.zgy.handle.userService.model.authority.role.RoleDTO;
import com.zgy.handle.userService.model.common.TransferDTO;
import com.zgy.handle.userService.service.base.QueryService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostQueryService extends QueryService<Post, PostDTO> {
    /**
     * 根据角色ID获取所有的账户信息
     * @param postId
     * @return
     */
    List<TransferDTO> getAccountListByPostId(Long postId);
}
