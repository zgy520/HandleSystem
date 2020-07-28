package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.model.authority.DepartmentPost;
import com.zgy.handle.userService.model.authority.DepartmentPostPK;
import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.structure.DepartPersonalType;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentAccount;
import com.zgy.handle.userService.model.structure.DepartmentAccountPK;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.structure.DepartmentAccountRepository;
import com.zgy.handle.userService.repository.structure.DepartmentPostRepository;
import com.zgy.handle.userService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DepartmentPostService extends SystemService<DepartmentPost,DepartmentPost> {
    private DepartmentPostRepository departmentPostRepository;
    public DepartmentPostService(DepartmentPostRepository departmentPostRepository) {
        super(departmentPostRepository);
        this.departmentPostRepository = departmentPostRepository;
    }

    public void setDepartmentPost(Post post, Department department){
        DepartmentPost departmentPost = DepartmentPost.builder()
                .post(post)
                .department(department)
                .build();
        departmentPostRepository.deleteByPostId(post.getId()); // 删除已有数据
        DepartmentPostPK departmentPostPK = new DepartmentPostPK();
        departmentPostPK.setPostId(post.getId());
        departmentPostPK.setDepartId(department.getId());
        departmentPost.setId(departmentPostPK);
        departmentPostRepository.save(departmentPost);
    }

    /**
     * 根据人员id获取部门
     * @param postId
     * @return
     */
    public Department getByPostId(Long postId){
        List<DepartmentPost> departmentPostList = departmentPostRepository.findByPostId(postId);
        if (departmentPostList != null && departmentPostList.size() > 0){
            return departmentPostList.get(0).getDepartment();
        }
        return null;
    }

    public List<Post> getPostListByDepartmentId(Long departId){
        List<DepartmentPost> departmentPostList = departmentPostRepository.findByDepartmentId(departId);
        if (departmentPostList != null && departmentPostList.size() > 0){
            return departmentPostList.stream().map(DepartmentPost::getPost).collect(Collectors.toList());
        }
        return null;
    }

    public int deleteByPostId(Long accountId){
        return departmentPostRepository.deleteByPostId(accountId);
    }

    public void relatePostsByDepartmentId(Department department, Set<Post> postSet){
        this.deleteByDepartId(department.getId());
        postSet.stream().forEach(post -> {
            setDepartmentPost(post,department);
        });
    }

    public void deleteByDepartId(Long departId){
        departmentPostRepository.deleteByDepartmentId(departId);
    }
}
