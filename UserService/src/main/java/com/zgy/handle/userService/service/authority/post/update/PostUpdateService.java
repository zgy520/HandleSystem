package com.zgy.handle.userService.service.authority.post.update;

import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.PostDTO;
import com.zgy.handle.userService.service.base.UpdateService;

public interface PostUpdateService extends UpdateService<Post, PostDTO> {
    String relateUser(Long postId, String selectedUserList);
}
