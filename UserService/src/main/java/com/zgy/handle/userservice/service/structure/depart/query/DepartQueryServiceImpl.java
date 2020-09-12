package com.zgy.handle.userservice.service.structure.depart.query;

import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.depart.DepartQueryDTO;
import com.zgy.handle.userservice.model.common.TransferDTO;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.structure.Enterprise;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.repository.authority.post.PostQueryRepository;
import com.zgy.handle.userservice.repository.structure.depart.DepartQueryRepository;
import com.zgy.handle.userservice.repository.user.query.AccountQueryRepository;
import com.zgy.handle.userservice.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.userservice.service.structure.DepartmentAccountServiceBase;
import com.zgy.handle.userservice.service.structure.DepartmentPostServiceBase;
import com.zgy.handle.userservice.util.tree.TreeConvert;
import lombok.extern.slf4j.Slf4j;
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
public class DepartQueryServiceImpl extends BaseQueryServiceImpl<Department, DepartQueryDTO> implements DepartQueryService {

    @Autowired
    private AccountQueryRepository accountQueryRepository;
    @Autowired
    private PostQueryRepository postQueryRepository;
    @Autowired
    private DepartmentAccountServiceBase departmentAccountService;
    @Autowired
    private DepartmentPostServiceBase departmentPostService;
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
