package com.zgy.handle.userService.service.authority.post;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.PostDTO;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.authority.PostRepository;
import com.zgy.handle.userService.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public ResponseCode<List<String>> findSelectedUsersByPostId(Long postId){
        ResponseCode<List<String>> responseCode = ResponseCode.sucess();
        Post post = postRepository.findById(postId).get();
        Set<Account> accounts = post.getAccountSet();
        responseCode.setData(accounts.stream().map(Account::getId).map(String::valueOf).collect(Collectors.toList()));
        return responseCode;
    }

    /**
     * 根据岗位id列表获取所对应的岗位
     * @param postIdList
     * @return
     */
    public Set<Post> findByPostIdIn(List<Long> postIdList){
        return postRepository.findAllByIdIn(postIdList);
    }
    
}
