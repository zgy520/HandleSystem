package com.zgy.handle.userService.controller.post;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.post.convert.PostMapper;
import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.PostDTO;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.authority.post.PostService;
import com.zgy.handle.userService.service.user.AccountService;
import com.zgy.handle.userService.util.Str.StrUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping(value = "post")
@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;
    private final AccountService accountService;


    /**
     * 获取角色列表
     * @param pageable
     * @param postDTO
     * @return
     */
    @GetMapping(value = "list")
    public ResponseCode<List<PostDTO>> getAccountList(@PageableDefault(page = 1,size = 10) Pageable pageable, PostDTO postDTO){
        pageable = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), Sort.Direction.DESC,"updateTime");
        ResponseCode<List<PostDTO>> responseCode = ResponseCode.sucess();

        Page<Post> postList = postService.findAllByDynamicQuery(pageable,postDTO);
        List<Post> posts = postList.getContent();
        List<PostDTO> roleDTOList = postMapper.toPostDTOs(posts);
        responseCode.setPageInfo(postList);
        responseCode.setData(roleDTOList);
        return responseCode;
    }

    @PostMapping(value = "update")
    public ResponseCode<PostDTO> update(@RequestBody PostDTO postDTO){
        ResponseCode<PostDTO> responseCode = ResponseCode.sucess();
        log.info("获取到的数据为:" + postDTO);
        List<Long> userIdList = StrUtils.transformList(postDTO.getUserList(),Long::parseLong);
        Set<Account> accountSet = accountService.findByIdIn(userIdList);
        Post post = postMapper.toPost(postDTO);
        post.setAccountSet(accountSet);
        postService.update(StringUtils.isBlank(postDTO.getId())?null : Long.valueOf(postDTO.getId()),post);
        responseCode.setData(postMapper.toPostDTO(post));
        return responseCode;
    }

    @ApiOperation("根据id获取数据")
    @GetMapping(value = "find/{id}")
    public ResponseCode<PostDTO> findById(@PathVariable(value = "id") Long id){
        ResponseCode<PostDTO> responseCode = ResponseCode.sucess();
        Optional<Post> optionalRole = postService.findById(id);
        if (!optionalRole.isPresent()){
            throw new EntityNotFoundException("角色中对应id={" + id + "}的数据不存在");
        }
        responseCode.setData(postMapper.toPostDTO(optionalRole.get()));
        return responseCode;
    }

    @GetMapping(value = "getUserListById/{id}")
    public ResponseCode<List<String>> getUsreListById(@PathVariable(value = "id") Long id){
        return postService.findSelectedUsersByPostId(id);
    }
    @ApiOperation("删除账号")
    @DeleteMapping(value = "delete/{id}")
    public ResponseCode<PostDTO> delete(@PathVariable(value = "id") Long id){
        Optional<Post> optionalRole = postService.findById(id);
        ResponseCode<PostDTO> responseCode = ResponseCode.sucess();
        if (optionalRole.isPresent())
            responseCode.setData(postMapper.toPostDTO(optionalRole.get()));
        else
            throw new EntityNotFoundException("找不到id对应为:" + id.toString() + "的值");
        postService.delete(id);
        return responseCode;
    }
    @GetMapping(value = "getPostList")
    public ResponseCode<List<SelectDTO>> getPostList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<Post> postList = postService.findAll();
        List<SelectDTO> selectDTOList = new ArrayList<>();
        postList.stream().forEach(post -> {
            SelectDTO selectDTO = new SelectDTO(post.getId().toString(),post.getName());
            selectDTOList.add(selectDTO);
        });
        responseCode.setData(selectDTOList);
        return responseCode;
    }
}
