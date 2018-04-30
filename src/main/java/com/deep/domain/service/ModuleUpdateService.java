package com.deep.domain.service;

import com.deep.api.request.ModuleFindRequest;
import com.deep.api.request.ModuleUpdateRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.DisinfectFilesModel;
import com.deep.domain.model.ImmunePlanModel;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.domain.util.JudgeUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用于专家/监督员 查询 更新
 * 包括6大生产环节
 * create by zhongrui on 18-4-29.
 */
@Service
public class ModuleUpdateService {
    @Resource
    private DisinfectFilesService disinfectFilesService;
    @Resource
    private ImmunePlanService immunePlanService;
    @Resource
    private RepellentPlanService repellentPlanService;

    /**
     * 需要身份验证
     * 集合查询
     * request.mark = 1 : dis 模块
     * request.mark = 2 : imm 模块
     * request.mark = 3 : rep 模块
     * @param request 查询请求
     * @return 查询结果
     */
    public Response findByChoice(ModuleFindRequest request){
        //默认分页
        if (request.getSize() == 0 ){
            request.setSize(10);
        }
        //身份为专家
        if ("4".equals(request.getAuth()) ||
                "8".equals(request.getAuth()) ||
                "12".equals(request.getAuth()) ||
                "16".equals(request.getAuth()) ) {
            if ("1".equals(request.getMark())) {
                List<DisinfectFilesModel> list = this.disinfectFilesService.getDisinfectFilesModelByIsPassCheck(request , new RowBounds(request.getPage() * request.getSize() , request.getSize()));
                return JudgeUtil.JudgeFind(list , list.size());

            } else if ("2".equals(request.getMark())) {
                List<ImmunePlanModel> list = this.immunePlanService.getImmunePlanModelByIsPassCheck(request , new RowBounds(request.getPage() * request.getSize() , request.getSize()));
                return JudgeUtil.JudgeFind(list , list.size());

            } else if ("3".equals(request.getMark())) {
                List<RepellentPlanModel> list = this.repellentPlanService.getRepellentPlanModelByIsPassCheck(request , new RowBounds(request.getPage() * request.getSize() , request.getSize()));
                return JudgeUtil.JudgeFind(list , list.size());
            }

        //身份为监督员
        } else if ("20".equals(request.getAuth()) ){
            if ("1".equals(request.getMark())) {
                List<DisinfectFilesModel> list = this.disinfectFilesService.getDisinfectFilesModelByIsPassSup(request , new RowBounds(request.getPage() * request.getSize() , request.getSize()));
                return JudgeUtil.JudgeFind(list , list.size());

            } else if ("2".equals(request.getMark())) {
                List<ImmunePlanModel> list = this.immunePlanService.getImmunePlanModelByIsPassSup(request , new RowBounds(request.getPage() * request.getSize() , request.getSize()));
                return JudgeUtil.JudgeFind(list , list.size());

            } else if ("3".equals(request.getMark())) {
                List<RepellentPlanModel> list = this.repellentPlanService.getRepellentPlanModelByIsPassSup(request , new RowBounds(request.getPage() * request.getSize() , request.getSize()));
                return JudgeUtil.JudgeFind(list , list.size());
            }
        }
        return JudgeUtil.JudgeFind(null);

    }

    /**
     * 需要身份验证
     * 模块更新
     * request.mark = 1 : dis 模块
     * request.mark = 2 : imm 模块
     * request.mark = 3 : rep 模块
     * @param request 更新请求
     * @return 更新结果
     */
    public Response updateByChoice(ModuleUpdateRequest request){
        //专家
        if ("4".equals(request.getAuth()) ||
                "8".equals(request.getAuth()) ||
                "12".equals(request.getAuth()) ||
                "16".equals(request.getAuth())){
            if ( request.getUnpassReason() == null){
                return Responses.errorResponse("Lack Item");
            } else {
                if ("1".equals(request.getMark())){
                    int row = this.disinfectFilesService.updateDisinfectFilesModelByProfessor(request);
                    return JudgeUtil.JudgeUpdate(row);

                } else if ("2".equals(request.getMark())){
                    int row = this.immunePlanService.updateImmunePlanModelByProfessor(request);
                    return JudgeUtil.JudgeUpdate(row);

                } else if ("3".equals(request.getMark())){
                    int row = this.repellentPlanService.updateRepellentPlanModelByProfessor(request);
                    return JudgeUtil.JudgeUpdate(row);
                }
            }

        } else if ("20".equals(request.getAuth())){
            if ("1".equals(request.getMark())){
                int row = this.disinfectFilesService.updateDisinfectFilesModelBySupervisor(request);
                return JudgeUtil.JudgeUpdate(row);

            } else if ("2".equals(request.getMark())){
                int row = this.immunePlanService.updateImmunePlanModelBySupervisor(request);
                return JudgeUtil.JudgeUpdate(row);

            } else if ("3".equals(request.getMark())){
                int row = this.repellentPlanService.updateRepellentPlanModelBySupervisor(request);
                return JudgeUtil.JudgeUpdate(row);
            }
        }
        return JudgeUtil.JudgeFind(null);
    }

}
