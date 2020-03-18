package com.zgy.handle.handleService.model.meta.structure.enterprise.xml;

import lombok.Data;

@Data
public class XmlCol {
    private String name;
    private String desc;
    private String coltype;
    private int length;

    public XmlCol(){

    }

    public XmlCol(String name, String desc, String coltype, int length){
        this.name = name;
        this.desc = desc;
        this.coltype = coltype;
        this.length = length;
    }
}
