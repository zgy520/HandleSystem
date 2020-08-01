package com.zgy.handle.userService.service.structure.depart.query;

import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.depart.DepartQueryDTO;
import com.zgy.handle.userService.model.common.TransferDTO;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.authority.post.PostQueryRepository;
import com.zgy.handle.userService.repository.structure.depart.DepartQueryRepository;
import com.zgy.handle.userService.repository.user.AccountRepository;
import com.zgy.handle.userService.repository.user.query.AccountQueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import com.zgy.handle.userService.service.structure.DepartmentAccountService;
import com.zgy.handle.userService.service.structure.DepartmentPostService;
import com.zgy.handle.userService.util.tree.TreeConvert;
import lombok.extern.slf4j.Slf4j;
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
public class DepartQueryServiceImpl extends QueryServiceImpl<Department, DepartQueryDTO> implements DepartQueryService {

    @Autowired
    private AccountQueryRepository accountQueryRepository;
    @Autowired
    private PostQueryRepository postQueryRepository;
    @Autowired
    private DepartmentAccountService departmentAccountService;
    @Autowired
    private DepartmentPostService departmentPostService;
    private DepartQueryRepository departQueryRepository;
    @Autowired
    public DepartQueryServiceImpl(DepartQueryRepository departQueryRepository) {
        super(departQueryRepository);
        this.departQueryRepository = departQueryRepository;
    }

    @Override
    public List<DepartQueryDTO> getDepartmentDtoList(List<DepartQueryDTO> departQueryDTOList){
        departQueryDTOList.stream().forEach(departmentDTO -> {
            Enterprise enterprise = this.fetchIndustry(Long.valueOf(departmentDTO.getId()));
            if (enterprise != null){
                departmentDTO.setEnterpriseName(enterprise.getName());
                departmentDTO.setEnterpriseId(enterprise.getId().toString());
            }
        });
        TreeConvert treeUtils = new TreeConvert(departQueryDTOList);
        try {
            List<DepartQueryDTO> departmentDTOS = treeUtils.toJsonArray(DepartQueryDTO.class);
            return departmentDTOS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransferDTO> getAccountListByDepartId(Long departId) {
        List<TransferDTO> selectDTOList = new ArrayList<>();
        Optional<Department> departmentOptional = departQueryRepository.findById(departId);
        List<Account> accountList = accountQueryRepository.findAll();
        accountList.stream().forEach(account -> {
            TransferDTO transferDTO = new TransferDTO(account.getId().toString(),account.getName(),false);
            selectDTOList.add(transferDTO);
        });
        if (departmentOptional.isPresent()){
            //Department department = departmentOptional.get();
            List<Account> accountSet = departmentAccountService.getAccountListByDepartmentId(departId);
            if (accountSet != null) {
                Set<String> accountIdSet = accountSet.stream().map(Account::getId).map(String::valueOf).collect(Collectors.toSet());
                selectDTOList.stream().forEach(transferDTO -> {
                    if (accountIdSet.contains(transferDTO.getKey())){
                        transferDTO.setSelected(true);
                    }
                });
            }
            return selectDTOList;
        }
        return selectDTOList;
    }

    @Override
    public List<TransferDTO> getPostListByDepartId(Long departId) {
        List<TransferDTO> selectDTOList = new ArrayList<>();
        Optional<Department> departmentOptional = departQueryRepository.findById(departId);
        List<Post> postList = postQueryRepository.findAll();
        postList.stream().forEach(account -> {
            TransferDTO transferDTO = new TransferDTO(account.getId().toString(),account.getName(),false);
            selectDTOList.add(transferDTO);
        });
        if (departmentOptional.isPresent()){
            //Department department = departmentOptional.get();
            List<Post> postSet = departmentPostService.getPostListByDepartmentId(departId);
            if (postSet != null) {
                Set<String> postIdSet = postSet.stream().map(Post::getId).map(String::valueOf).collect(Collectors.toSet());
                selectDTOList.stream().forEach(transferDTO -> {
                    if (postIdSet.contains(transferDTO.getKey())){
                        transferDTO.setSelected(true);
                    }
                });
            }
            return selectDTOList;
        }
        return selectDTOList;
    }


    @Override
    public Specification<Department> querySpecification(DepartQueryDTO dto) {
        return null;
    }


    /**
     * 根据部门id获取部门对应的企业信息
     * @param departId
     * @return
     */
    @Transactional(readOnly = true)
    public Enterprise fetchIndustry(Long departId){
        Enterprise enterprise = null;
        Optional<Department> departmentOptional = this.findById(departId);
        if (departmentOptional.isPresent()){
            enterprise = departmentOptional.get().getEnterprise();
        }
        return enterprise;
    }
}
