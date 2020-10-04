package com.zgy.handle.userservice.service.authority.post.update;

import com.zgy.handle.common.model.common.UniqueInfo;
import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.PostDTO;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.repository.authority.post.PostQueryRepository;
import com.zgy.handle.userservice.repository.authority.post.PostUpdateRepository;
import com.zgy.handle.userservice.repository.user.query.AccountQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PosUpdateServiceImpl extends BaseUpdateServiceImpl<Post, PostDTO> implements PostUpdateService {
    private PostUpdateRepository postUpdateRepository;
    @Autowired
    private PostQueryRepository postQueryRepository;
    @Autowired
    private AccountQueryRepository accountQueryRepository;

    @Autowired
    public PosUpdateServiceImpl(PostUpdateRepository postUpdateRepository) {
        super(postUpdateRepository);
        this.postUpdateRepository = postUpdateRepository;
    }

    @Override
    public void fillRelateObj(PostDTO postDTO, Post post) {
        if (StringUtils.isNotBlank(postDTO.getId())) {
            Optional<Post> oldPostOptional = postQueryRepository.findById(Long.valueOf(postDTO.getId()));
            post.setAccountSet(oldPostOptional.get().getAccountSet());
        }
    }

    @Override
    public UniqueInfo checkUnique(PostDTO postDTO, Post post) {
        Long count = StringUtils.isBlank(postDTO.getId()) ? postQueryRepository.countByCodeOrName(postDTO.getCode(), postDTO.getName()) : postQueryRepository.countByCodeAndIdNot(postDTO.getCode(), Long.valueOf(postDTO.getId()));
        if (count > 0) {
            return UniqueInfo.getUniqueInfo("角色代码或名称重复");
        }
        return super.checkUnique(postDTO, post);
    }

    @Override
    public String relateUser(Long postId, String selectedUserList) {
        String result = "成功";
        Optional<Post> postOptional = postQueryRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            List<Long> userIdList = Arrays.asList(selectedUserList.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
            Set<Account> accountList = accountQueryRepository.findByIdIn(userIdList).stream().collect(Collectors.toSet());
            post.setAccountSet(accountList);
            postUpdateRepository.save(post);
            return result;
        } else {
            throw new EntityNotFoundException("不存在ID为：" + postId.toString() + "的岗位信息");
        }
    }
}
