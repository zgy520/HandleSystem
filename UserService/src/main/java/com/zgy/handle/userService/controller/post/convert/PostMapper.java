package com.zgy.handle.userService.controller.post.convert;

import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.PostDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDTO toPostDTO(Post post);
    List<PostDTO> toPostDTOs(List<Post> posts);
    Post toPost(PostDTO postDTO);
}
