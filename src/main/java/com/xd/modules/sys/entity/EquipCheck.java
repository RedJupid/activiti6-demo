package com.xd.modules.sys.entity;

import lombok.Data;

@Data
public class EquipCheck {
    private Long id;

    private String equipname;

    private String manufacturer;

    private String informbill;

    private String applybill;

    private String recordbill;

    private String lackbill;

    private String addbill;

    private String processId;

}