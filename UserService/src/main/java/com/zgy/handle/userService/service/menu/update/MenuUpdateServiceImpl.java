package com.zgy.handle.userService.service.menu.update;

import com.zgy.handle.userService.model.common.UniqueInfo;
import com.zgy.handle.userService.model.dto.menu.MenuUpdateDTO;
import com.zgy.handle.userService.model.menu.Button;
import com.zgy.handle.userService.model.menu.Menu;
import com.zgy.handle.userService.repository.menu.ButtonQueryRepository;
import com.zgy.handle.userService.repository.menu.MenuQueryRepository;
import com.zgy.handle.userService.repository.menu.MenuUpdateRepository;
import com.zgy.handle.userService.service.base.impl.UpdateServiceImpl;
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
public class MenuUpdateServiceImpl extends UpdateServiceImpl<Menu, MenuUpdateDTO> implements MenuUpdateService {
    @Autowired
    private MenuQueryRepository menuQueryRepository;
    @Autowired
    private ButtonQueryRepository buttonQueryRepository;
    private MenuUpdateRepository menuUpdateRepository;

    public MenuUpdateServiceImpl(MenuUpdateRepository menuUpdateRepository) {
        super(menuUpdateRepository);
        this.menuUpdateRepository = menuUpdateRepository;
    }

    @Override
    public void fillRelateObj(MenuUpdateDTO menuUpdateDTO, Menu menu) {
        if (StringUtils.isNotBlank(menuUpdateDTO.getParentId())) {
            Optional<Menu> parentMenuOptioanl = baseRepository.findById(Long.valueOf(menuUpdateDTO.getParentId()));
            if (parentMenuOptioanl.isPresent()) {
                menu.setParent(parentMenuOptioanl.get());
            } else {
                log.error("未能发现id为：" + menuUpdateDTO.getParentId() + "得菜单");
            }
        }
    }

    @Override
    public UniqueInfo checkUnique(MenuUpdateDTO menuUpdateDTO, Menu menu) {
        Integer count = StringUtils.isBlank(menuUpdateDTO.getId()) ? menuQueryRepository.countByMenuNameOrCode(menuUpdateDTO.getName(), menuUpdateDTO.getCode()) :
                menuQueryRepository.countByNameOrCodeAndIdNot(menuUpdateDTO.getName(), menuUpdateDTO.getCode(), menuUpdateDTO.getId());
        if (count != null) {
            return UniqueInfo.getUniqueInfo("菜单编码或名称重复,请确认!");
        }
        return super.checkUnique(menuUpdateDTO, menu);
    }

    @Override
    public String relateButton(Long menuId, String selectedButtonList) {
        String result = "成功";
        Optional<Menu> optionalMenu = menuQueryRepository.findById(menuId);
        if (optionalMenu.isPresent()){
            Menu menu = optionalMenu.get();
            List<Long> buttonIdList = Arrays.asList(selectedButtonList.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
            Set<Button> buttonSet = buttonQueryRepository.findByIdIn(buttonIdList).stream().collect(Collectors.toSet());
            menu.setBtnSet(buttonSet);
            menuUpdateRepository.save(menu);
            return result;
        }else {
            throw new EntityNotFoundException("不存在ID为：" + menuId.toString() + "的角色信息");
        }
    }
}
