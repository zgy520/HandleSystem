package com.zgy.handle.userService.controller.post.convert;

import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.PostDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-18T11:45:17+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDTO toPostDTO(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        if ( post.getId() != null ) {
            postDTO.setId( String.valueOf( post.getId() ) );
        }
        postDTO.setName( post.getName() );
        postDTO.setCode( post.getCode() );
        postDTO.setNote( post.getNote() );

        return postDTO;
    }

    @Override
    public List<PostDTO> toPostDTOs(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostDTO> list = new ArrayList<PostDTO>( posts.size() );
        for ( Post post : posts ) {
            list.add( toPostDTO( post ) );
        }

        return list;
    }

    @Override
    public Post toPost(PostDTO postDTO) {
        if ( postDTO == null ) {
            return null;
        }

        Post post = new Post();

        if ( postDTO.getId() != null ) {
            post.setId( Long.parseLong( postDTO.getId() ) );
        }
        post.setNote( postDTO.getNote() );
        post.setCode( postDTO.getCode() );
        post.setName( postDTO.getName() );

        return post;
    }
}
