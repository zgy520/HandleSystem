package com.zgy.handle.cardservice.service;

import com.zgy.handle.cardservice.model.Equipment;
import com.zgy.handle.cardservice.repository.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 * @author: a4423
 * @date: 2020/10/4 9:15
 */
@Service
@Slf4j
public class EquipmentServiceImpl implements EquipmentService{
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Override
    public void update() {
        Equipment equipment = new Equipment();
        equipment.setModel("HK");
        equipment.setName("行车记录仪");
        equipment.setManufacturer("大华股份");
        equipment.setPrice(300000d);
        equipment.setType("ZY");
        equipment.getImages().add("preFile.png");
        equipment.getImages().add("postFile.png");
        equipment.getImages().add("aFile.png");
        equipmentRepository.save(equipment);
    }

    @Override
    @Transactional
    public Equipment findById(Long equipmentId) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipmentId);
        if (equipmentOptional.isPresent()){
            Set<String> fileNameSet = equipmentOptional.get().getImages();
            log.info("获取到的文件数量为:{}",fileNameSet.size());
            fileNameSet.forEach(item -> log.info(item));
        }
        return null;
    }
}
