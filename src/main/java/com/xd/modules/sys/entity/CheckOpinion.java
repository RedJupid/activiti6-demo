package com.xd.modules.sys.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CheckOpinion {
    private Long id;

    private String checker;

    private Date checktime;

    private Integer pass;

    private String opinion;

    private String processId;

}