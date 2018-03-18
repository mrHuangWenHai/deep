package com.deep.domain.service;

import com.deep.domain.model.FactoryModel;
import com.deep.domain.model.PermitModel;
import com.deep.infra.persistence.sql.mapper.PermitMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermitService {
    @Resource
    private PermitMapper permitMapper;

    /**
     * 获取所有的权限信息
     * @return
     */
    public List<PermitModel> getAll() {
        return permitMapper.queryAllPermit();
    }

    /**
     * 根据ID单个权限的信息
     * @param id
     * @return
     */
    public PermitModel getOnePermit(int id) {
        return permitMapper.queryPermitById(id);
    }

    /**
     * 根据Permit_ID获取权限信息
     * @param id
     * @return
     */
    public PermitModel getOnePermitByPermitId(int id) {
        return permitMapper.queryPermitById(id);
    }

    /**
     * 修改权限的名称等
     * @param permitModel
     * @return
     */
    public Long addPermit(PermitModel permitModel) {
        return permitMapper.insertPermit(permitModel);
    }

    /**
     * 删除某个权限
     * @param id
     * @return
     */
    public Long deletePermit(Long id) {
        return permitMapper.deletePermit(id);
    }
}
