package com.zgy.handle.userservice.service.menu.query;

import com.zgy.handle.userservice.model.common.TransferDTO;
import com.zgy.handle.userservice.model.dto.menu.MenuQueryDTO;
import com.zgy.handle.userservice.model.menu.Button;
import com.zgy.handle.userservice.model.menu.Menu;
import com.zgy.handle.userservice.model.menu.Menu_;
import com.zgy.handle.userservice.repository.menu.MenuQueryRepository;
import com.zgy.handle.userservice.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.userservice.util.tree.TreeConvert;
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
public class MenuQueryServiceImpl extends BaseQueryServiceImpl<Menu, MenuQueryDTO> implements MenuQueryService {
    @Autowired
    private ButtonQueryService buttonQueryService;
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

    @Transactional(readOnly = true)
    @Override
    public List<TransferDTO> getButtonListByMenuId(Long menuId) {
        List<TransferDTO> selectDTOList = new ArrayList<>();
        Optional<Menu> menuOptional = menuQueryRepository.findById(menuId);
        List<Button> buttonList = buttonQueryService.findAll();
        buttonList.stream().forEach(button -> {
            TransferDTO transferDTO = new TransferDTO(button.getId().toString(),button.getName(),false);
            selectDTOList.add(transferDTO);
        });
        if (menuOptional.isPresent()){
            Menu menu = menuOptional.get();
            Set<Button> buttonSet = menu.getBtnSet();
            List<String> buttonIdList = buttonSet.stream().map(Button::getId).map(String::valueOf).collect(Collectors.toList());
            selectDTOList.stream().forEach(selectDto -> {
                if (buttonIdList.contains(selectDto.getKey())){
                    selectDto.setSelected(true);
                }
            });
        }
        return selectDTOList;
    }

    @Override
    public List<Menu> findAllByIdIn(List<Long> menuIdList) {
        return menuQueryRepository.findByIdIn(menuIdList);
    }
}
