package com.wh.stat.domain;

/**
 *
 * @author 万浩
 * @data 2019/12/4 18:43
 * @description
 */
public class BaseEntity {
    private String name;
    private Double value;

    public BaseEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
