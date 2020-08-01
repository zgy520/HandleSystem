package com.zgy.handle.userService.service.menu.query;

import com.zgy.handle.userService.model.dto.menu.MenuQueryDTO;
import com.zgy.handle.userService.model.menu.Menu;
import com.zgy.handle.userService.model.menu.Menu_;
import com.zgy.handle.userService.repository.menu.MenuQueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import com.zgy.handle.userService.util.tree.TreeConvert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuQueryServiceImpl extends QueryServiceImpl<Menu, MenuQueryDTO> implements MenuQueryService {
    private MenuQueryRepository menuQueryRepository;

    public MenuQueryServiceImpl(MenuQueryRepository menuQueryRepository) {
        super(menuQueryRepository);
        this.menuQueryRepository = menuQueryRepository;
    }


    @Override
    public Specification<Menu> querySpecification(MenuQueryDTO dto) {
        Specification<Menu> specification = Specification
                .where(StringUtils.isBlank(dto.getName()) ? null : menuQueryRepository.fieldLike(Menu_.NAME, dto.getName()))
                .and(StringUtils.isBlank(dto.getCode()) ? null : menuQueryRepository.fieldLike(Menu_.CODE, dto.getCode()))
                .and(StringUtils.isBlank(dto.getNote()) ? null : menuQueryRepository.fieldLike(Menu_.NOTE, dto.getNote()));
        return specification;
    }

    @Override
    public List<Menu> findBySpecification(MenuQueryDTO menuQueryDTO) {
        return menuQueryRepository.findAll(querySpecification(menuQueryDTO));
    }

    @Override
    public List<MenuQueryDTO> getTreeMenuQueryDto(List<MenuQueryDTO> menuQueryDTOList) {
        TreeConvert treeConvert = new TreeConvert(menuQueryDTOList);
        try {
            return treeConvert.toJsonArray(MenuQueryDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
