package com.activiti6.controller.common;

import java.io.Serializable;

public class MyFormProperty implements Serializable {

    private static final long serialVersionUID = 4755461461301528307L;

    private String id;

    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
