package com.zgy.handle.cardservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.*;

/**
 * @author: a4423
 * @date: 2020/9/26 20:20
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment extends BaseModel {
    private String name;
    /**
     * 设备型号
     */
    private String model;
    private Double price;
    /**
     * 制造商
     */
    private String manufacturer;
    private String type;

    @ElementCollection
    @CollectionTable(name = "equipment_image",
                        joinColumns = @JoinColumn(name = "equipID"))
    @Column(name = "filename")
    @org.hibernate.annotations.OrderBy(clause = " filename desc")
    protected Set<String> images = new LinkedHashSet<>();
}
