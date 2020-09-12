package com.zgy.handle.userservice.service.authority.post.update;

import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.PostDTO;
import com.zgy.handle.userservice.service.base.UpdateService;

public interface PostUpdateService extends UpdateService<Post, PostDTO> {
    String relateUser(Long postId, String selectedUserList);
}
