package com.deep.domain.service;

import com.deep.domain.model.SheepInfo;
import com.deep.domain.model.SheepInfoExample;
import com.deep.infra.persistence.sql.mapper.SheepInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created By LeeBoom On 2018/7/25 23:46
 */
@Service
public class SheepInfoService {
    @Resource
    private SheepInfoMapper sheepInfoMapper;

    public List<SheepInfo> selectNullBcId(SheepInfoExample sheepInfoExample)
    {
        return sheepInfoMapper.selectByExample(sheepInfoExample);
    }

    public Integer updateSheepInfoById(SheepInfo sheepInfo)
    {
         return sheepInfoMapper.updateByPrimaryKeySelective(sheepInfo);
    }
    public void insertRecord(SheepInfo sheepInfo)
    {
        sheepInfoMapper.insert(sheepInfo);
    }
}
