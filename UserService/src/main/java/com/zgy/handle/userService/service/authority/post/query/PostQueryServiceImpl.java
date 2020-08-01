package com.zgy.handle.userService.service.authority.post.query;

import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.PostDTO;
import com.zgy.handle.userService.model.authority.Post_;
import com.zgy.handle.userService.model.authority.role.Role;
import com.zgy.handle.userService.model.authority.role.RoleDTO;
import com.zgy.handle.userService.model.authority.role.Role_;
import com.zgy.handle.userService.model.common.TransferDTO;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.authority.post.PostQueryRepository;
import com.zgy.handle.userService.repository.authority.role.RoleQueryRepository;
import com.zgy.handle.userService.repository.base.QueryRepository;
import com.zgy.handle.userService.repository.user.query.AccountQueryRepository;
import com.zgy.handle.userService.service.authority.role.query.RoleQueryService;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostQueryServiceImpl extends QueryServiceImpl<Post, PostDTO> implements PostQueryService {
    private PostQueryRepository postQueryRepository;
    @Autowired
    private AccountQueryRepository accountQueryRepository;
    @Autowired
    public PostQueryServiceImpl(PostQueryRepository postQueryRepository){
        super(postQueryRepository);
        this.postQueryRepository = postQueryRepository;
    }
    public PostQueryServiceImpl(QueryRepository queryRepository) {
        super(queryRepository);
    }

    @Override
    public Specification<Post> querySpecification(PostDTO dto) {
        Specification<Post> roleSpecification = Specification.where(StringUtils.isNotBlank(dto.getCode())? postQueryRepository.fieldLike(Post_.CODE,dto.getCode()) : null)
                .and(StringUtils.isNotBlank(dto.getName())? postQueryRepository.fieldLike(Post_.NAME,dto.getName()) : null)
                .and(StringUtils.isNotBlank(dto.getNote())? postQueryRepository.fieldLike(Post_.NOTE,dto.getNote()):null);
        return roleSpecification;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransferDTO> getAccountListByPostId(Long roleId){
        List<TransferDTO> selectDTOList = new ArrayList<>();
        Optional<Post> postOptional = postQueryRepository.findById(roleId);
        List<Account> accountList = accountQueryRepository.findAll();
        accountList.stream().forEach(account -> {
            TransferDTO transferDTO = new TransferDTO(account.getId().toString(),account.getName(),false);
            selectDTOList.add(transferDTO);
        });
        if (postOptional.isPresent()){
            Post post = postOptional.get();
            Set<Account> accountSet = post.getAccountSet();
            Set<String> accountIdSet = accountSet.stream().map(Account::getId).map(String::valueOf).collect(Collectors.toSet());
            selectDTOList.stream().forEach(transferDTO -> {
                if (accountIdSet.contains(transferDTO.getKey())){
                    transferDTO.setSelected(true);
                }
            });
            return selectDTOList;
        }
        return selectDTOList;
    }

}
