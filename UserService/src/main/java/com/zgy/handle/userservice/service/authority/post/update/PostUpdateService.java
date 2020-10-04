package com.zgy.handle.userservice.service.authority.post.update;

import com.zgy.handle.common.service.base.UpdateService;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.PostDTO;

public interface PostUpdateService extends UpdateService<Post, PostDTO> {
    String relateUser(Long postId, String selectedUserList);
}
