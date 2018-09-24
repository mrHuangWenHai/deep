package com.deep.domain.service.SheepInfo;

import com.deep.api.request.SheepUpdateRequest;
import com.deep.api.response.DeadSheepInformationResponse;
import com.deep.api.response.NoBuildingColResponse;
import com.deep.api.response.SheepInformationResponse;
import com.deep.domain.model.sheepInfo.SheepInformationModel;
import com.deep.infra.persistence.sql.mapper.sheepInfo.SheepInformationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SheepInformationService {
    @Resource
    private SheepInformationMapper sheepInformationMapper;

    /**
     * 添加一只羊的信息
     * @param sheepInformationModel 羊的具体信息
     * @return 返回插入结果
     */
    public Long insertSheepInformation(SheepInformationModel sheepInformationModel) {
        return sheepInformationMapper.insertSheepInformation(sheepInformationModel);
    }

    /**
     * 修改一只羊的栏栋信息
     * @param buildingColumn 栏栋号码信息
     * @param id 羊id
     * @return 返回修改的结果
     */
    public Long updateSheepInformation(Long buildingColumn, Long id) {
        return sheepInformationMapper.updateSheepInformation(buildingColumn, id);
    }

    /**
     * 修改一只羊的售出信息
     * @param id 羊场号码
     * @param flag 标志
     * @return 是否修改成功的标志
     */
    public Long updateSaleSheepInformation(Long id, Byte flag) {
        return sheepInformationMapper.updateSaleSheepInformation(id, flag);
    }

    /**
     * 修改死亡羊只的信息
     * @param dead 是否死亡
     * @param reason 死亡原因
     * @param method 处理方法
     * @param date 死亡日期
     * @return 返回修改的结果
     */
    public Long updateDeadSheepInformation(Byte dead, String reason, String method, Timestamp date, Long id) {
        return sheepInformationMapper.updateDeadSheepInformation(dead, reason, method, date, id);
    }

    /**
     * 查看所有没有死亡的羊只
     * @param factory 羊场号码
     * @param start 初始编号
     * @param size 分页大小
     * @return 所有羊的数目
     */
    public List<SheepInformationResponse> selectAllSheep(Long factory, Long start, Byte size) {
        return sheepInformationMapper.selectAllSheep(factory, start, size);
    }

    /**
     * 查看所有没有死亡的羊只的数目
     * @param factory 羊场号码
     * @return 所有羊的数目
     */
    public Long countAllSheep(Long factory) {
        return sheepInformationMapper.countAllSheep(factory);
    }

    /**
     * 查找死亡羊只的信息
     * @param factory 羊场号码
     * @param start 初始编号
     * @param size 每页条数
     * @return 返回死亡羊只的数据
     */
    public List<DeadSheepInformationResponse> selectDeadSheep(Long factory, Long start, Byte size) {
        return sheepInformationMapper.selectDeadSheep(factory, start, size);
    }

    /**
     * 查找死亡羊只的数目
     * @param factory 羊场号码
     * @return 返回结果
     */
    public Long countDeadSheep(Long factory) {
        return sheepInformationMapper.countDeadSheep(factory);
    }

    /**
     * 查看没有分配栏栋的羊只
     * @param factory 羊场编号
     * @return 返回所有的羊只
     */
    public List<NoBuildingColResponse> getNoBuildingColumn(Long factory) {
        return sheepInformationMapper.getNoBuildingSheep(factory);
    }

    /**
     * 批量设置栏栋
     * @param sheeps 所选羊只
     */
    public void setNoBuildingColumn(List<Long> sheeps, Long buildingCol, Long factory) {
        sheepInformationMapper.setBuildingSheep(sheeps.toString(), buildingCol, factory);
    }

    /**
     * 更新羊只的信息
     * @param request 相关请求
     * @return 返回成功或者失败的标志
     */
    public Long updateSheepInfo(SheepUpdateRequest request) {
        return sheepInformationMapper.updateTag(request.getFactory(), request.getSheepid(), request.getBreedingSheepBase(), request.getTradeMarkEartag());
    }

    /**
     * 获取羊的商标耳牌号
     * @param factory 羊场号
     * @param buildingColumn 栏栋号列表
     * @return 返回羊的商标耳牌号码
     */
    public List<String> getSheepEarTag(Long factory, List<Long> buildingColumn) {
        if (buildingColumn.size() == 0) return new LinkedList<>();
        return sheepInformationMapper.getAllTrademarkByFactoryIDAndBuildingColumn(factory, buildingColumn);
    }

    /**
     * 获取羊的免疫耳牌号
     * @param factory 羊场号
     * @param buildingColumn 栏栋号列表
     * @return 返回羊的商标耳牌号码
     */
    public List<String> getSheepImmuneTag(Long factory, List<Long> buildingColumn) {
        if (buildingColumn == null) return new LinkedList<>();
        return sheepInformationMapper.getAllImmuneMarkByFactoryIDAndBuildingColumn(factory, buildingColumn);
    }

    /**
     * 根据羊的商标耳牌号和羊场号获取羊的id信息
     * @param tradeMarkTagTag 商标耳牌号
     * @param factory 羊场
     * @return return
     */
    public Long getSheepIdByTradeMarkTag(String tradeMarkTagTag, Long factory) {
        // 首先要使用正则表达式验证商标耳牌的合法性
        // 编译正则表达式的合法性
        Pattern pattern = Pattern.compile("^M[0-9]{6}+$");
        // 要验证的表达式
        Matcher matcher = pattern.matcher(tradeMarkTagTag);
        // 判断合法性
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            // 耳牌号合法
            // 如果超过一个耳牌号，进行异常处理
            try {
                return sheepInformationMapper.getSheepIdByTradeMarkTag(tradeMarkTagTag, factory);
            } catch (Exception ex) {
                System.out.println("羊场耳牌号重复！");
                return null;
            }
        }
        return null;
    }
}
