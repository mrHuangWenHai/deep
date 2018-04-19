package com.deep.domain.service;

import com.deep.domain.model.NoticePlan;
import com.deep.domain.model.NoticePlanExample;
import com.deep.infra.persistence.sql.mapper.NoticePlanMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * author: Created  By  Caojiawei
 * date: 2018/3/8  20:13
 */
@Service
public class NoticePlanService {
    @Resource
    private NoticePlanMapper noticePlanMapper;

    public int addPlan(NoticePlan noticePlan){
        return this.noticePlanMapper.insert(noticePlan);
    }
    public int dropPlan(Integer id){
        return this.noticePlanMapper.deleteByPrimaryKey(id);
    }
    public int changePlan(NoticePlan noticePlan){
        return this.noticePlanMapper.updateByPrimaryKeySelective(noticePlan);
    }
    public NoticePlan findPlanById(Integer id){
        return this.noticePlanMapper.selectByPrimaryKey(id);
    }
    public List<NoticePlan> findPlanSelective(NoticePlanExample noticePlanExample, RowBounds rowBounds){
        return this.noticePlanMapper.selectByExampleWithBLOBsWithRowbounds(noticePlanExample,rowBounds);
    }
    public List<NoticePlan> selectInSite(String string){
        return this.noticePlanMapper.selectInSite(string);
    }
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+/*Token中表明上传者身份的信息*/fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
