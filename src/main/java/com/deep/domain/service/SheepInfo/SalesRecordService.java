package com.deep.domain.service.SheepInfo;

import com.deep.api.request.SalesRecordRequest;
import com.deep.domain.model.sheepInfo.SalesRecordModel;
import com.deep.domain.model.sheepInfo.SheepInformationModel;
import com.deep.infra.persistence.sql.mapper.sheepInfo.SalesRecordMapper;
import com.deep.infra.persistence.sql.mapper.sheepInfo.SheepInformationMapper;
import org.springframework.stereotype.Service;
import sun.security.provider.SHA;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SalesRecordService {
    @Resource
    private SalesRecordMapper salesRecordMapper;
    @Resource
    private SheepInformationMapper sheepInformationMapper;

    public Long insertRecord(SalesRecordModel salesRecordModel) {
        return salesRecordMapper.insertRecord(salesRecordModel);
    }

    public List<SalesRecordModel> getSalesRecords(Long factory, Long start, Byte size) {
        return salesRecordMapper.selectSaleRecords(factory, start, size);
    }

    public Long salesNumber(Long factory) {
        return salesRecordMapper.selectSaleRecordsNumber(factory);
    }

    public List<SalesRecordModel> getBuyRecords(Long factory, Long start, Byte size) {
        return salesRecordMapper.selectBuyRecords(factory, start, size);
    }

    public Long buyNumber(Long factory) {
        return salesRecordMapper.selectBuyRecordsNumber(factory);
    }

    /**
     * 进行销售
     * @return 是否成功的结果
     */
    public Boolean doSales(SalesRecordRequest salesRecordRequest) {

        if (salesRecordRequest.getSheep() == null) {
            return false;
        }
        SalesRecordModel model = new SalesRecordModel();

        model.setStartFactory(salesRecordRequest.getStartFactory());
        model.setStartName(salesRecordRequest.getStartName());
        model.setEndFactory(salesRecordRequest.getEndFactory());
        model.setEndName(salesRecordRequest.getEndName());

        for (int i = 0; i < salesRecordRequest.getSheep().size(); i++) {
            // 首先修改羊只的置位信息, flag为1表示售出
            Long modifySheepInFactory = sheepInformationMapper.updateSaleSheepInformation(salesRecordRequest.getSheep().get(i).getId(), (byte)1);
            if (modifySheepInFactory < 0) return false;
            // 添加一条记录到销售记录表中

            model.setImmuneEarTag(salesRecordRequest.getSheep().get(i).getImmuneEarTag());
            model.setTrademarkEarTag(salesRecordRequest.getSheep().get(i).getTrademarkEarTag());

            Long insertSalesRecords = salesRecordMapper.insertRecord(model);
            if (insertSalesRecords < 0) {
                sheepInformationMapper.updateSaleSheepInformation(salesRecordRequest.getSheep().get(i).getId(), (byte)0);
                return false;
            }
            // 首先查找出原来羊的信息
            SheepInformationModel sheepBefore = sheepInformationMapper.getOneSheep(salesRecordRequest.getSheep().get(i).getId());
            // 新建一只羊信息
            sheepBefore.setFactory(salesRecordRequest.getEndFactory());
            sheepBefore.setId(null);
            sheepBefore.setBuildingColumn(null);
            sheepBefore.setSale((byte)0);

            sheepInformationMapper.insertSheepInformation(sheepBefore);
        }
        return true;
    }
}
