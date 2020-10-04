package com.zgy.handle.cardservice.service;

import com.zgy.handle.cardservice.model.Equipment;

/**
 * @author: a4423
 * @date: 2020/10/4 9:15
 */
public interface EquipmentService {
    void update();
    Equipment findById(Long equipmentId);
}
