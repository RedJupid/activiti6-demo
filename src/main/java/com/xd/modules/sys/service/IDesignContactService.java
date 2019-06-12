package com.xd.modules.sys.service;

import com.xd.modules.sys.entity.DesignContact;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.modules.sys.vo.DesignContactVO;

import java.util.List;

/**
 * <p>
 * 设计联络 服务类
 * </p>
 *
 * @author zxd
 * @since 2019-06-12
 */
public interface IDesignContactService extends IService<DesignContact> {
    List<DesignContactVO> selectWithProAndCom(String processId);
}
