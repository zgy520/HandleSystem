package com.zgy.handle.userservice.service.structure.depart.update;

import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.depart.DepartUpdateDTO;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.structure.Enterprise;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.repository.authority.post.PostQueryRepository;
import com.zgy.handle.userservice.repository.structure.depart.DepartUpdateRepository;
import com.zgy.handle.userservice.repository.user.query.AccountQueryRepository;
import com.zgy.handle.userservice.service.structure.depart.query.DepartQueryService;
import com.zgy.handle.userservice.service.structure.enterprise.query.EnterpriseQueryService;
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
public class DepartUpdateServiceImpl extends BaseUpdateServiceImpl<Department, DepartUpdateDTO> implements DepartUpdateService {
    @Autowired
    private DepartQueryService departQueryService;
    @Autowired
    private EnterpriseQueryService enterpriseQueryService;
    @Autowired
    private AccountQueryRepository accountQueryRepository;
    @Autowired
    private PostQueryRepository postQueryRepository;
    /* @Autowired
     private DepartmentAccountServiceBase departmentAccountService;
     @Autowired
     private DepartmentPostServiceBase departmentPostService;*/
    private DepartUpdateRepository departUpdateRepository;

    @Autowired
    public DepartUpdateServiceImpl(DepartUpdateRepository departUpdateRepository) {
        super(departUpdateRepository);
        this.departUpdateRepository = departUpdateRepository;
    }

    @Override
    public void fillRelateObj(DepartUpdateDTO departUpdateDTO, Department department) {
        if (StringUtils.isNotBlank(departUpdateDTO.getParentId())) {
            Optional<Department> superDepartmentOptional = departQueryService.findById(Long.valueOf(departUpdateDTO.getParentId()));
            if (superDepartmentOptional.isPresent()) {
                department.setParent(superDepartmentOptional.get());
            }
        }
        if (StringUtils.isNotBlank(departUpdateDTO.getEnterpriseId())) {
           /* Optional<Enterprise> enterpriseOptional = null; //enterpriseQueryService.findById(Long.valueOf(departUpdateDTO.getEnterpriseId()));
            department.setEnterprise(enterpriseOptional.get());*/
        }
    }

    @Override
    public String relateUser(Long departId, String selectedUserList) {
        String result = "成功";
        Optional<Department> optionalDepartment = departQueryService.findById(departId);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            List<Long> userIdList = Arrays.asList(selectedUserList.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
            Set<Account> accountList = accountQueryRepository.findByIdIn(userIdList).stream().collect(Collectors.toSet());
            //departmentAccountService.relateAccountsByDepartmentId(department,accountList);

            return result;
        } else {
            throw new EntityNotFoundException("不存在ID为：" + departId.toString() + "的岗位信息");
        }
    }

    @Override
    public String relatePost(Long departId, String selectedPostList) {
        String result = "成功";
        Optional<Department> optionalDepartment = departQueryService.findById(departId);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            List<Long> postIdList = Arrays.asList(selectedPostList.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
            Set<Post> postList = postQueryRepository.findByIdIn(postIdList).stream().collect(Collectors.toSet());
            //departmentPostService.relatePostsByDepartmentId(department,postList);
            return result;
        } else {
            throw new EntityNotFoundException("不存在ID为：" + departId.toString() + "的岗位信息");
        }
    }
}
