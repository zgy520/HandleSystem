package com.zgy.handle.handleService.model.meta.structure.enterprise.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ListRecord {
    @XmlAttribute
    private String metadataStandard;
    @XmlAttribute
    private String parseType;
    private XmlHeader header;
    private XmlData metadata;

    public ListRecord(){}
    public ListRecord(String metadataStandard, String parseType, XmlHeader header, XmlData metadata){
        this.metadataStandard = metadataStandard;
        this.parseType = parseType;
        this.header = header;
        this.metadata = metadata;
    }
}
