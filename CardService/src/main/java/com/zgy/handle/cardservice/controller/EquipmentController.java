package com.zgy.handle.cardservice.controller;

import com.zgy.handle.cardservice.model.Equipment;
import com.zgy.handle.cardservice.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: a4423
 * @date: 2020/10/4 9:16
 */
@RestController(value = "equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(value = "update")
    public void updateEquipment(){
        equipmentService.update();
    }
    @GetMapping(value = "list")
    public void listById(Long equipmentId){
        equipmentService.findById(equipmentId);
    }
}
