package com.zgy.handle.userservice.controller.post.convert;

import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.PostDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author a4423
 */
@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDTO toPostDTO(Post post);
    List<PostDTO> toPostDTOs(List<Post> posts);
    Post toPost(PostDTO postDTO);
}
