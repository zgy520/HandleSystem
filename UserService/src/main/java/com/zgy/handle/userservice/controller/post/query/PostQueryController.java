package com.zgy.handle.userservice.controller.post.query;


import com.zgy.handle.common.controller.base.BaseQueryController;
import com.zgy.handle.common.model.common.SelectDTO;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.controller.post.convert.PostMapper;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.PostDTO;
import com.zgy.handle.userservice.model.common.TransferDTO;
import com.zgy.handle.userservice.service.authority.post.query.PostQueryService;
import com.zgy.handle.userservice.service.authority.post.update.PostUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "post/query")
@Slf4j
public class PostQueryController extends BaseQueryController<Post, PostDTO> {
    @Autowired
    private PostMapper postMapper;
    private PostQueryService postQueryService;
    private PostUpdateService postUpdateService;

    public PostQueryController(PostUpdateService postUpdateService, PostQueryService postQueryService) {
        super(postUpdateService, postQueryService);
        this.postQueryService = postQueryService;
        this.postUpdateService = postUpdateService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Post> posts) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        posts.forEach(post -> {
            SelectDTO selectDTO = new SelectDTO(post.getId().toString(),post.getName(),post.getId().toString());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @Override
    public List<PostDTO> convertTtoU(List<Post> posts) {
        return postMapper.toPostDTOs(posts);
    }

    @Override
    public PostDTO convertTtoU(Post post) {
        return postMapper.toPostDTO(post);
    }

    @Override
    public Post convertUtoT(PostDTO postDTO) {
        return postMapper.toPost(postDTO);
    }

    @GetMapping(value = "getAccountListByPostId")
    public ResponseCode<List<TransferDTO>> getAccountListByRoleId(Long postId) {
        ResponseCode<List<TransferDTO>> responseCode = ResponseCode.sucess();
        /*Set<Account> accountSet = roleQueryService.getAccountListByRoleId(roleId);
        accountSet.stream().forEach(account -> {
            SelectDTO selectDTO = new SelectDTO(account.getId().toString(),account.getName(),account.getId().toString());
            selectDTOList.add(selectDTO);
        });*/
        responseCode.setData(postQueryService.getAccountListByPostId(postId));
        return responseCode;
    }
}
