package com.deep.api.resource;

import com.deep.api.Utils.ExtendTableUtil;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.ExtendModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 拓展数据库的功能
 * 此功能只有管理员拥有权限
 */
@RestController
@RequestMapping(value = "extend")
public class ExtendTableResource {
    private final Logger logger = LoggerFactory.getLogger(ExtendTableResource.class);

    /**
     * 方法功能: 创建数据表
     * @param extendModel 拓展模块类型
     * @return response数据类型
     */
    @Permit(authorities = "add_expansion_module")
    @PostMapping(value = "/add/")
    public Response createTable(@RequestBody ExtendModel extendModel) {
        logger.info("invoke createTable{}, url is extend/add/", extendModel);
        if (!ExtendTableUtil.createTable(extendModel.getTableName(), extendModel.getColumns())) {
            return Responses.errorResponse("failed to create a table");
        } else {
            return Responses.successResponse();
        }
    }

    /**
     * 方法功能:删除一个数据表
     * @param tableName 数据表格名称
     * @return response相关信息
     */
    @Permit(authorities = "delete_extension_module")
    @DeleteMapping(value = "/delete/{tableName}")
    public Response deleteTable(@PathVariable("tableName") String tableName) {
        logger.info("invoke deleteTable{}, url is extend/delete/{tableName}", tableName);
        if (!ExtendTableUtil.dropTable(tableName)) {
            return Responses.errorResponse("failed to drop a table");
        } else {
            return Responses.successResponse();
        }
    }

    /**
     * 方法功能:获取用户自定义的表格信息
     * @return response相关信息
     */
    @Permit(authorities = "query_expansion_module")
    @GetMapping(value = "/tableLists")
    public Response getAllTable() {
        logger.info("invoke getAllTable");
        Map<String, Object> userExtendedTables =  ExtendTableUtil.getAllTable();
        if (userExtendedTables == null) {
            return Responses.errorResponse("查询错误!");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("success", userExtendedTables);
        hashMap.put("size", userExtendedTables.size());
        response.setData(hashMap);
        return response;
    }

    /**
     * 获取用户自定义表的列名称
     */
    @Permit(authorities = "query_expansion_module")
    @GetMapping(value = "/columnLists/{tableName}")
    public Response getTableColumns(@PathVariable("tableName") String tableName) {
        logger.info("invoke getTableColumns{}, url is extend/columnLists/{tableName}", tableName);
        Map<String, String> userExtendedTablesColumns =  ExtendTableUtil.getAllColumns(tableName);
        if (userExtendedTablesColumns == null) {
            return Responses.errorResponse("查询错误!");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("success", userExtendedTablesColumns);
        hashMap.put("size", userExtendedTablesColumns.size());
        response.setData(hashMap);
        return response;
    }
}
