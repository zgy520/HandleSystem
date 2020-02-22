package com.zgy.handle.userService.service.authority.post;

import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.PostDTO;
import com.zgy.handle.userService.repository.authority.PostRepository;
import com.zgy.handle.userService.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PostService extends SystemService<Post> {
    private PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository){
        super(postRepository);
        this.postRepository = postRepository;
    }

    public Post findByName(String postName){
        return this.postRepository.findByName(postName);
    }

    public Page<Post> findAllByDynamicQuery(Pageable pageable, PostDTO postD){
        Specification<Post> specification = Specification
                .where(postD.getName() == null? null : PostRepository.fieldContains("name",postD.getName()))
                .and(postD.getCode() == null? null : PostRepository.fieldContains("code",postD.getCode()));
        return postRepository.findAll(specification,pageable);
    }
    
}
