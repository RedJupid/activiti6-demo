package com.xd.modules.sys.service.impl;

import com.xd.modules.sys.entity.DesignContact;
import com.xd.modules.sys.mapper.DesignContactMapper;
import com.xd.modules.sys.service.IDesignContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.modules.sys.vo.DesignContactVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设计联络 服务实现类
 * </p>
 *
 * @author zxd
 * @since 2019-06-12
 */
@Service
public class DesignContactServiceImpl extends ServiceImpl<DesignContactMapper, DesignContact> implements IDesignContactService {

    @Override
    public List<DesignContactVO> selectWithProAndCom(String processId) {
        return this.baseMapper.selectWithProAndCom(processId);
    }
}
