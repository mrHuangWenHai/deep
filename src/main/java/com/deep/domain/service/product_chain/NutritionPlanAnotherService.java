package com.deep.domain.service.product_chain;

import com.deep.api.Utils.AgentUtil;
import com.deep.api.request.NutritionAddRequest;
import com.deep.api.request.ProfessorRequest;
import com.deep.api.request.SupervisorRequest;
import com.deep.domain.model.NutritionAnotherPlanModel;
import com.deep.infra.persistence.sql.mapper.product_chain.NutritionAnotherPlanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NutritionPlanAnotherService {
    @Resource
    private NutritionAnotherPlanMapper mapper;

    /**
     * 插入一条记录
     * @param request 请求体
     * @return 返回是否成功的标志
     */
    public Boolean insertRecord(NutritionAddRequest request) {
        NutritionAnotherPlanModel model = new NutritionAnotherPlanModel();
        BeanUtils.copyProperties(request, model);
        System.out.println("this is the nutritionPlanAnotherModel" + "\n" + model.toString());
        return mapper.insertRecord(model) > 0;
    }

    /**
     * 删除一条记录
     * @param id 记录编号
     * @return 返回是否成功的标志
     */
    public Boolean deleteRecord(Long id) {
        return mapper.deleteRecord(id) > 0;
    }

    /**
     * 操作员修改一条记录
     * @param id 记录编号
     * @return 返回是否成功的标志
     */
    public Boolean updateAllTermsOfOneRecord(NutritionAddRequest request, Long id) {
        NutritionAnotherPlanModel model = new NutritionAnotherPlanModel();
        BeanUtils.copyProperties(request, model);
        return mapper.updateAllTermsOfRecord(model, id) > 0;
    }

    /**
     * 专家审核
     * @return 审核是否成功的标志
     */
    public Boolean updateProfessorTermsOfOneRecord(ProfessorRequest request, Long id) {
        return mapper.updateProfessorTermsOfRecord(request, id) > 0;
    }

    /**
     * 监督员审核
     * @return 审核是否成功的标志
     */
    public Boolean updateSupervisorTermsOfOneRecord(SupervisorRequest request, Long id) {
        return mapper.updateSupervisorTermsOfRecord(request, id) > 0;
    }

    /**
     * 查询某个羊场下的所有数据
     * @return 分页数据
     */
    public List<NutritionAnotherPlanModel> selectRecords(Byte flag, Long start, Long factory, String factoryName, Byte isPassCheck, String startTime, String endTime, String earTag) {
        List<Long> lists = getLists(flag, factory);
        if (lists == null) return null;
        return mapper.selectRecords(start, lists, factoryName, isPassCheck, startTime, endTime, earTag);
    }

    /**
     * 查询记录的条数
     * @return 记录数目
     */
    public Long countRecords(Byte flag, Long factory, String factoryName, Byte isPassCheck, String startTime, String endTime, String earTag) {
        List<Long> lists = getLists(flag, factory);
        if (lists == null || lists.size() == 0) return null;
        return mapper.countRecords(lists, factoryName, isPassCheck, startTime, endTime, earTag);
    }

    /**
     * 获取直属羊场的记录条数
     * @return 记录数目
     */
    public Long countDirectRecords(Long factory, String factoryName, Byte isPassCheck, String startTime, String endTime, String earTag) {
        List<Long> lists = getDirectLists(factory);
        if (lists == null || lists.size() == 0) return null;
        return mapper.countRecords(lists, factoryName, isPassCheck, startTime, endTime, earTag);
    }

    /**
     * 查询一条数据
     * @return 根据id返回的结果
     */
    public NutritionAnotherPlanModel selectRecord(Long id) {
        return mapper.selectRecord(id);
    }

    /**
     * 查询指定期间的营养实施档案
     * @return 指定期间营养实施档案列表
     */
    public List<NutritionAnotherPlanModel> selectRecordByNutritionT(Date startDate, Date endDate,  Long factory) {
        return mapper.selectRecordByNutritionT(startDate, endDate, factory);
    }


    /**
     * 获取代理的下属羊场列表
     * @return 返回列表
     */
    private List<Long> getLists(Byte flag, Long factory) {
        List<Long> lists = new ArrayList<>();
        if (flag == 0) lists.add(factory);
        if (flag == 1) {
            Map<Long, List<Long> > factories = AgentUtil.getAllSubordinateFactory(String.valueOf(factory));
            if (factories == null) {
                return null;
            }
            // direct people
            List<Long> directFactories = factories.get((long) -1);
            // not direct people
            List<Long> unDirectFactories = factories.get((long) 0);
            lists.addAll(directFactories);
            lists.addAll(unDirectFactories);
        }
        return lists;
    }

    /**
     * 获取代理的直属下属羊场列表
     * @return 返回列表
     */
    private List<Long> getDirectLists(Long factory) {
        Map<Long, List<Long> > factories = AgentUtil.getAllSubordinateFactory(String.valueOf(factory));
        if (factories == null) {
            return null;
        }
        // direct people
        return factories.get((long) -1);
    }
}
