package com.xd.modules.sys.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Problem {
    private Long id;

    private String title;

    private String type;

    private String degree;

    private Date startTime;

    private Date endTime;

    private Date warmTime;

    private String status;

    private String schemeText;

    private String schemeFile;

    private String processId;

}