package com.deep.api.resource.SheepInfo;

import com.deep.api.request.DeadSheepRequest;
import com.deep.api.response.DeadSheepInformationResponse;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.api.response.SheepInformationResponse;
import com.deep.domain.model.sheepInfo.SalesRecordModel;
import com.deep.domain.service.SheepInfo.SalesRecordService;
import com.deep.domain.service.SheepInfo.SheepInformationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "ds")
public class DeadSaleInfoResource {
    @Resource
    private SheepInformationService sheepInformationService;
    @Resource
    private SalesRecordService salesRecordService;

    /**
     * 列出所有死亡的羊只
     * @param factory 羊场编号
     * @param start 初始条的编号
     * @param size 每一页的大小
     * @return 返回的结果
     */
    @GetMapping(value = "/d/{factory}")
    public Response selectDeadSheep(@PathVariable("factory") Long factory,
                                    @RequestParam("start") Long start,
                                    @RequestParam("size") Byte size) {
        List<DeadSheepInformationResponse> deadSheepInformationResponses = sheepInformationService.selectDeadSheep(factory, start, size);
        Long number = sheepInformationService.countDeadSheep(factory);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("dead", deadSheepInformationResponses);
        data.put("number", number);
        response.setData(data);
        return response;
    }

    /**
     * 列出未死亡的所有羊只
     * @param factory 羊场编号
     * @param start 初始条的编号
     * @param size 每一页的大小
     * @return 返回的结果
     */
    @GetMapping(value = "/a/{factory}")
    public Response selectAllSheep(@PathVariable("factory") Long factory,
                                    @RequestParam("start") Long start,
                                    @RequestParam("size") Byte size) {
        List<SheepInformationResponse> allSheepInformationResponses = sheepInformationService.selectAllSheep(factory, start, size);
        Long number = sheepInformationService.countAllSheep(factory);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("all", allSheepInformationResponses);
        data.put("number", number);
        response.setData(data);
        return response;
    }

    /**
     * 羊只死亡处理接口
     * @param deadSheepRequest 要请求的信息
     * @return 返回处理结果
     */
    @PatchMapping(value = "/d/d")
    public Response updateOneSheepToDead(@RequestBody DeadSheepRequest deadSheepRequest) {
        System.out.println(deadSheepRequest.toString());
        deadSheepRequest.setDead((byte)1);
        deadSheepRequest.setDate(new Timestamp(System.currentTimeMillis()));
        Long update = sheepInformationService.updateDeadSheepInformation(deadSheepRequest.getDead(), deadSheepRequest.getReason(), deadSheepRequest.getMethod(), deadSheepRequest.getDate(), deadSheepRequest.getId());
        if (update > 0) {
            return Responses.successResponse();
        } else {
            return Responses.errorResponse("更新失败！");
        }
    }

    /**
     * 查看已经出售的羊只
     * @param factory 羊场号
     * @param start 初始
     * @param size 每页大小
     * @return 一页数据
     */
    @GetMapping(value = "/s/{factory}")
    public Response getSales(@PathVariable("factory") Long factory,
                               @RequestParam("start") Long start,
                               @RequestParam("size") Byte size) {
        List<SalesRecordModel> sales = salesRecordService.getSalesRecords(factory, start, size);
        Long number = salesRecordService.salesNumber(factory);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("all", sales);
        data.put("number", number);
        response.setData(data);
        return response;
    }

    /**
     * 查看已经买入的羊只
     * @param factory 羊场号
     * @param start 初始
     * @param size 每页大小
     * @return 一页数据
     */
    @GetMapping(value = "/b/{factory}")
    public Response getBuy(@PathVariable("factory") Long factory,
                             @RequestParam("start") Long start,
                             @RequestParam("size") Byte size) {
        List<SalesRecordModel> buys = salesRecordService.getBuyRecords(factory, start, size);
        Long number = salesRecordService.buyNumber(factory);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("all", buys);
        data.put("number", number);
        response.setData(data);
        return response;
    }

    /**
     * 进行羊只出售操作
     * @param id 羊只的id
     * @param salesRecordModel 出售的信息
     * @return 返回相关信息
     */
    @PostMapping(value = "/ds/{id}")
    public Response doSales(@PathVariable("id") Long id, @RequestBody SalesRecordModel salesRecordModel) {
        if (salesRecordService.doSales(id, salesRecordModel)) {
            return Responses.successResponse();
        } else {
            return Responses.errorResponse("销售羊只失败！");
        }
    }
}
