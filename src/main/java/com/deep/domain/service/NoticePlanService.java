package com.deep.domain.service;

import com.deep.domain.model.NoticePlan;
import com.deep.domain.model.NoticePlanExample;
import com.deep.infra.persistence.sql.mapper.NoticePlanMapper;
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
            int add = this.noticePlanMapper.insert(noticePlan);
            return add;
        }
        public int dropPlan(Integer id){
            int drop = this.noticePlanMapper.deleteByPrimaryKey(id);
            return drop;
        }
        public int changePlan(NoticePlan noticePlan){
            int change = this.noticePlanMapper.updateByPrimaryKeySelective(noticePlan);
            return change;
        }
        public NoticePlan findPlanById(Integer id){
            NoticePlan find = this.noticePlanMapper.selectByPrimaryKey(id);
            return find;
        }
        public List<NoticePlan> findPlanSelective(NoticePlanExample noticePlanExample){
            List<NoticePlan> find = this.noticePlanMapper.selectByExampleWithBLOBs(noticePlanExample);
            return find;
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
