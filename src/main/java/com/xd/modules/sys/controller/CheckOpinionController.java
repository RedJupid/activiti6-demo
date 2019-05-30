package com.xd.modules.sys.controller;

import com.xd.modules.BaseController;
import com.xd.modules.sys.entity.CheckOpinion;
import com.xd.modules.sys.service.CheckOpinionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/checkOpinion")
public class CheckOpinionController extends BaseController<CheckOpinionService, CheckOpinion> {



}
