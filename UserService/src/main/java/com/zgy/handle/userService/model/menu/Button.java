package com.zgy.handle.userService.model.menu;

import com.zgy.handle.userService.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "system_button")
@Slf4j
@Data
public class Button extends BaseModel {
    private String code;
    private String name;
    private String icon;
    private int serial;
    @ManyToMany(mappedBy = "btnSet")
    private Set<Menu> menuSet = new HashSet<>();

    public void addMenu(Menu menu){
        this.menuSet.add(menu);
        menu.getBtnSet().add(this);
    }

    public void removeMenu(Menu menu){
        this.menuSet.remove(menu);
        menu.getBtnSet().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Button button = (Button) o;
        return Objects.equals(code, button.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
