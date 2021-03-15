package com.zgy.handle.userservice.service.authority.role.query;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.common.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.userservice.dto.RoleMenuBtnDTO;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.authority.role.RoleDTO;
import com.zgy.handle.userservice.model.authority.role.Role_;
import com.zgy.handle.userservice.model.common.TransferDTO;
import com.zgy.handle.userservice.model.menu.Menu;
import com.zgy.handle.userservice.model.menu.RoleMenuButton;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.repository.authority.role.RoleMenuBtnQueryRepository;
import com.zgy.handle.userservice.repository.authority.role.RoleQueryRepository;
import com.zgy.handle.userservice.repository.menu.MenuQueryRepository;
import com.zgy.handle.userservice.repository.user.query.AccountQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleQueryServiceImpl extends BaseQueryServiceImpl<Role, RoleDTO> implements RoleQueryService {
    private RoleQueryRepository roleQueryRepository;
    @Autowired
    private AccountQueryRepository accountQueryRepository;
    @Autowired
    private RoleMenuBtnQueryRepository roleMenuBtnQueryRepository;
    @Autowired
    private MenuQueryRepository menuQueryRepository;

    @Autowired
    public RoleQueryServiceImpl(RoleQueryRepository roleQueryRepository) {
        super(roleQueryRepository);
        this.roleQueryRepository = roleQueryRepository;
    }

    public RoleQueryServiceImpl(QueryRepository queryRepository) {
        super(queryRepository);
    }

    @Override
    public Specification<Role> querySpecification(RoleDTO dto) {
        Specification<Role> roleSpecification = Specification.where(StringUtils.isNotBlank(dto.getCode()) ? roleQueryRepository.fieldLike(Role_.CODE, dto.getCode()) : null)
                .and(StringUtils.isNotBlank(dto.getName()) ? roleQueryRepository.fieldLike(Role_.NAME, dto.getName()) : null)
                .and(StringUtils.isNotBlank(dto.getNote()) ? roleQueryRepository.fieldLike(Role_.NOTE, dto.getNote()) : null);
        return roleSpecification;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransferDTO> getAccountListByRoleId(Long roleId) {
        List<TransferDTO> selectDTOList = new ArrayList<>();
        Optional<Role> roleOptional = roleQueryRepository.findById(roleId);
        List<Account> accountList = accountQueryRepository.findAll();
        accountList.stream().forEach(account -> {
            TransferDTO transferDTO = new TransferDTO(account.getId().toString(), account.getName(), false);
            selectDTOList.add(transferDTO);
        });
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            Set<Account> accountSet = role.getAccountSet();
            Set<String> accountIdSet = accountSet.stream().map(Account::getId).map(String::valueOf).collect(Collectors.toSet());
            selectDTOList.stream().forEach(transferDTO -> {
                if (accountIdSet.contains(transferDTO.getKey())) {
                    transferDTO.setSelected(true);
                }
            });
            return selectDTOList;
        }
        return selectDTOList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoleMenuBtnDTO> getPowerByRoleIdIn(List<Long> roleIdList) {
        List<RoleMenuBtnDTO> selectDTOList = new ArrayList<>();
        List<RoleMenuButton> roleMenuButtonByRoleIdIn = new ArrayList<>(); //roleMenuBtnQueryRepository.findByRoleIdIn(roleIdList); // 根据角色ID查RoleMenuBtnList
        List<Long> menuIDList = new ArrayList<>();
        for (int i = 0 ; i < roleMenuButtonByRoleIdIn.size() ; i++){
//            menuIDList.add(roleMenuButtonByRoleIdIn.get(i).getMenu().getId());
        }
        List uniqueMenu = menuIDList.stream().distinct().collect(Collectors.toList()); // 去重后的页面id
        List<Menu> roleMenuButtonByMenuIdList = menuQueryRepository.findByIdIn(uniqueMenu); // 根据页面id查所有的页面信息

        for (int i = 0 ; i < roleMenuButtonByMenuIdList.size() ; i++){
            RoleMenuBtnDTO roleMenuBtnDTO = new RoleMenuBtnDTO(roleMenuButtonByMenuIdList.get(i).getId(),roleMenuButtonByMenuIdList.get(i).getUrl(),null,roleMenuButtonByMenuIdList.get(i).getName());
            selectDTOList.add(roleMenuBtnDTO);
        }
        return selectDTOList;
    }

}
