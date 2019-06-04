package com.xd.modules.sys.service.impl;

import com.xd.modules.sys.entity.EquipCheck;
import com.xd.modules.sys.mapper.EquipCheckMapper;
import com.xd.modules.sys.service.IEquipCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.modules.sys.vo.EquipCheckVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设备开箱检查 服务实现类
 * </p>
 *
 * @author zxd
 * @since 2019-05-30
 */
@Service
public class EquipCheckServiceImpl extends ServiceImpl<EquipCheckMapper, EquipCheck> implements IEquipCheckService {

    @Override
    public List<EquipCheckVO> selectWithProAndCom(String processId) {
        return this.baseMapper.selectWithProAndCom(processId);
    }
}
