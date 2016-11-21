package com.techstar.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author lrzg on 16/11/18.
 * 描述：
 */
@Entity
public class Temp {
    @Id
    private Long id;
    private String data;
    @Property(nameInDb = "wendu")//定义列的别名
    private int value;
    @Transient
    private int tempCount; // 不使用
    public int getValue() {
        return this.value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 28410425)
    public Temp(Long id, String data, int value) {
        this.id = id;
        this.data = data;
        this.value = value;
    }
    @Generated(hash = 1524106993)
    public Temp() {
    }

    
}
