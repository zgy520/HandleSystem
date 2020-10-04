package com.zgy.handle.cardservice.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author: a4423
 * @date: 2020/10/4 10:36
 */
@Embeddable
@Data
public class Image {
    @Column(nullable = false)
    protected String title;
    @Column(nullable = false)
    private String fileName;
    protected int width;
    private int height;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image other = (Image) o;
        if (!title.equals(other.title)) {
            return false;
        }
        if (!fileName.equals(other.fileName)) {
            return false;
        }
        if (width != other.width) {
            return false;
        }
        if (height != other.height) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + fileName.hashCode();
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }
}
