package com.zgy.handle.handleService.model.meta.structure.enterprise.xml;

import lombok.Data;

import java.util.List;

@Data
public class XmlData {
    private List<XmlCol> col;

    public XmlData(){

    }
    public XmlData(List<XmlCol> col){
        this.col = col;
    }
}
