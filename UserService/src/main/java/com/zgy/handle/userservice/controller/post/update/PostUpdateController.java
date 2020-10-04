package com.zgy.handle.userservice.controller.post.update;

import com.zgy.handle.common.controller.base.BaseUpdateController;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.controller.post.convert.PostMapper;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.PostDTO;
import com.zgy.handle.userservice.service.authority.post.query.PostQueryService;
import com.zgy.handle.userservice.service.authority.post.update.PostUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "post/update")
@Slf4j
public class PostUpdateController extends BaseUpdateController<Post, PostDTO> {

    @Autowired
    private PostMapper postMapper;
    private PostQueryService postQueryService;
    private PostUpdateService postUpdateService;

    public PostUpdateController(PostUpdateService postUpdateService, PostQueryService postQueryService) {
        super(postUpdateService, postQueryService);
        this.postQueryService = postQueryService;
        this.postUpdateService = postUpdateService;
    }

    @Override
    public Post convertUtoT(PostDTO postDTO) {
        return postMapper.toPost(postDTO);
    }

    /**
     * 角色关联用户
     *
     * @param selectedUserList
     * @return
     */
    @PostMapping(value = "relateUser")
    public ResponseCode<String> relateUser(Long postId, String selectedUserList) {
        ResponseCode<String> responseCode = ResponseCode.sucess();
        log.info("角色ID为:" + postId.toString() + ",选择的用户为:" + selectedUserList);
        responseCode.setData(postUpdateService.relateUser(postId, selectedUserList));
        return responseCode;
    }
}
