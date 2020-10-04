package com.zgy.handle.cardservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author: a4423
 * @date: 2020/9/26 21:02
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Table(name = "equipment_check_record")
public class EquipmentCheckRecord extends BaseModel {
    private LocalDateTime checkTime;
    private String checkPerson;
    private String accountId;
    private String checkResult;
    @ManyToOne
    @JsonIgnore
    private Equipment equipment;
}
