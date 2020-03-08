package com.zgy.handle.userService.repository.authority;

import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends SystemRepository<Post>, JpaSpecificationExecutor<Post> {
    Post findByName(String postName);

    Set<Post> findAllByIdIn(List<Long> postIdList);

    static Specification<Post> fieldContains(String filed, String value){
        return (root,query,builder) -> builder.like(root.get(filed),contains(value));
    }

    static String contains(String expression){
        return MessageFormat.format("%{0}%",expression);
    }
}
