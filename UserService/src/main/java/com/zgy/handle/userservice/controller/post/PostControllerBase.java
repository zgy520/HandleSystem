package com.zgy.handle.userservice.controller.post;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.controller.BaseSystemController;
import com.zgy.handle.userservice.controller.post.convert.PostMapper;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.PostDTO;
import com.zgy.handle.userservice.model.user.SelectDTO;
import com.zgy.handle.userservice.service.authority.post.PostServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "post")
@RestController
public class PostControllerBase extends BaseSystemController<Post,PostDTO> {
    private PostServiceBase postService;
    @Autowired
    private PostMapper postMapper;

    public PostControllerBase(PostServiceBase postService) {
        super(postService);
        this.postService = postService;
    }


    @GetMapping(value = "getUserListById/{id}")
    public ResponseCode<List<String>> getUsreListById(@PathVariable(value = "id") Long id){
        return postService.findSelectedUsersByPostId(id);
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Post> posts) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        posts.stream().forEach(post -> {
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

    @GetMapping(value = "getPostList")
    public ResponseCode<List<SelectDTO>> getPostList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<Post> postList = postService.findAll();
        List<SelectDTO> selectDTOList = new ArrayList<>();
        postList.stream().forEach(post -> {
            SelectDTO selectDTO = new SelectDTO(post.getId().toString(),post.getName(),post.getId().toString());
            selectDTOList.add(selectDTO);
        });
        responseCode.setData(selectDTOList);
        return responseCode;
    }
}
