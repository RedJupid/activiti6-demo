package com.xd.modules.sys.controller;

import com.xd.modules.BaseController;
import com.xd.modules.sys.entity.Problem;
import com.xd.modules.sys.service.ProblemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/problem")
public class ProblemController extends BaseController<ProblemService, Problem> {


}
