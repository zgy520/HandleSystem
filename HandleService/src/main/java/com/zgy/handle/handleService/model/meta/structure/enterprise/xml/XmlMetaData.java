package com.zgy.handle.handleService.model.meta.structure.enterprise.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlMetaData {
    @XmlElement(name = "ListRecord")
    private ListRecord listRecord;

    public XmlMetaData(){}
    public XmlMetaData(ListRecord listRecord){
        this.listRecord = listRecord;
    }
}
