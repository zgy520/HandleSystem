package com.zgy.handle.userService.model.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "system_menu")
@Slf4j
@Data
public class Menu extends BaseModel {
    private String code;
    private String name;
    private String url;
    private String icon;
    private int serial;
    @ManyToOne
    @JoinColumn(name = "parentId")
    @JsonIgnore
    private Menu parent;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "system_menu_button",
            joinColumns = @JoinColumn(name = "menuId"),
            inverseJoinColumns = @JoinColumn(name = "btnId"))
    @JsonIgnore
    private Set<Button> btnSet = new HashSet<>();

    public void addBtn(Button button){
        btnSet.add(button);
        button.getMenuSet().add(this);
    }
    public void removeBtn(Button button){
        btnSet.remove(button);
        button.getMenuSet().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
