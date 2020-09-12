package com.zgy.handle.userservice.service.authority.post;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.PostDTO;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.repository.authority.PostRepository;
import com.zgy.handle.userservice.service.BaseSystemService;
import com.zgy.handle.userservice.service.user.AccountServiceBase;
import com.zgy.handle.userservice.util.Str.StrUtils;
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
public class PostServiceBase extends BaseSystemService<Post,PostDTO> {
    private PostRepository postRepository;
    @Autowired
    private AccountServiceBase accountService;
    @Autowired
    public PostServiceBase(PostRepository postRepository){
        super(postRepository);
        this.postRepository = postRepository;
    }

    public Post findByName(String postName){
        return this.postRepository.findByName(postName);
    }

    @Override
    public Page<Post> findByDynamicQuery(Pageable pageable, PostDTO postD){
        Specification<Post> specification = Specification
                .where(postD.getName() == null? null : PostRepository.fieldContains("name",postD.getName()))
                .and(postD.getCode() == null? null : PostRepository.fieldContains("code",postD.getCode()));
        return postRepository.findAll(specification,pageable);
    }

    @Override
    public void beforeUpdate(PostDTO postDTO, Post post) {
        List<Long> userIdList = StrUtils.transformList(postDTO.getUserList(),Long::parseLong);
        Set<Account> accountSet = accountService.findByIdIn(userIdList);
        post.setAccountSet(accountSet);
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
