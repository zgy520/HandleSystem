package com.zgy.handle.handleService.model.meta.structure.enterprise.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlHeader {
    private String identifier;
    @XmlElement(name = "reg-type")
    private int regType;
    @XmlElement(name = "parent-handle")
    private String parent_handle;

    public XmlHeader(){}
    public XmlHeader(String identifier, int regType, String parent_handle){
        this.identifier = identifier;
        this.regType = regType;
        this.parent_handle = parent_handle;
    }
}
