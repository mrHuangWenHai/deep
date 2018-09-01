package com.deep.domain.service;

import com.deep.domain.model.Saled;
import com.deep.domain.model.SaledExample;
import com.deep.infra.persistence.sql.mapper.sheepInfo.SaledMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created By LeeBoom On 2018/7/26 19:45
 */
@Service
public class SaledService {
    @Resource
    SaledMapper saledMapper;

    public List<Saled> selectSrc(SaledExample saledExample)
    {
        return saledMapper.selectByExample(saledExample);
    }
}
