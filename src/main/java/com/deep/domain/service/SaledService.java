package com.deep.domain.service;

import com.deep.domain.model.Saled;
import com.deep.domain.model.SaledExample;
import com.deep.infra.persistence.sql.mapper.SaledMapper;
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
        List<Saled> saleds = saledMapper.selectByExample(saledExample);
        return saleds;
    }
}
