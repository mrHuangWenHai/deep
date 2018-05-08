package com.deep.domain.service;

import com.deep.domain.model.CertificationModel;
import com.deep.domain.model.CertificationModelExample;
import com.deep.infra.persistence.sql.mapper.CertificationModelMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CertificationModelService {

    @Resource
    private CertificationModelMapper certificationModelMapper;

    public int insertCertification(CertificationModel certificationModel)
    {
        int flag =certificationModelMapper.insert(certificationModel);

        return flag;

    }

    public int deleteCertification(CertificationModelExample certificationModelExample)
    {
        int flag = certificationModelMapper.deleteByExample(certificationModelExample);

        return flag;
    }

    public List<CertificationModel> findAllCertification(CertificationModelExample certificationModelExample,
                                                         int pageNumb,int limit)
    {
        int offset=(pageNumb-1)*limit;

        RowBounds rowBounds=new RowBounds(offset,limit);

        List<CertificationModel>find = this.certificationModelMapper.selectByExampleWithRowbounds(certificationModelExample,rowBounds);

        return find;
    }
}
