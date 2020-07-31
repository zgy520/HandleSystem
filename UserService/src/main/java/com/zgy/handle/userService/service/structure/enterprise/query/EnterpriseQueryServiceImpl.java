package com.zgy.handle.userService.service.structure.enterprise.query;

import com.zgy.handle.userService.model.dto.structure.EnterpriseQueryDTO;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import com.zgy.handle.userService.repository.structure.enterprise.EntperiseQueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseQueryServiceImpl extends QueryServiceImpl<Enterprise, EnterpriseQueryDTO> implements EnterpriseQueryService {
    private EntperiseQueryRepository entperiseQueryRepository;
    public EnterpriseQueryServiceImpl(EntperiseQueryRepository entperiseQueryRepository) {
        super(entperiseQueryRepository);
        this.entperiseQueryRepository = entperiseQueryRepository;
    }

    @Override
    public Specification<Enterprise> querySpecification(Pageable pageable, EnterpriseQueryDTO dto) {
        return null;
    }
}
