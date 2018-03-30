package com.deep.api.resource;

import com.deep.api.Utils.ExtendTableUtil;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 在拓展数据库上的操作请求
 */
@RestController
@RequestMapping(value = "extend/operator")
public class ExtendOperatorResource {
    /**
     * 获取某一个数据表中的所有记录
     * @param tableName
     * @return
     */
    @GetMapping(value = "/lists/{tablename}")
    public Response getAllLists(@PathVariable("tablename") String tableName) {
        if (!ExtendTableUtil.isExistTable(tableName)) {
            return Responses.errorResponse("数据表不存在, 请重新创建");
        } else {
            List<Object> objects = ExtendTableUtil.getAllRows(tableName);
            if (objects == null) {
                return Responses.errorResponse("数据表中没有数据");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("data", objects);
            hashMap.put("size", objects.size());
            response.setData(hashMap);
            return response;
        }
    }

    /**
     * 查询某个表中的一条数据
     * @param tableName
     * @param id
     * @return
     */
    @GetMapping(value = "lists/{tablename}/{id}")
    public Response getOne(@PathVariable("tablename") String tableName, @PathVariable("id") String id) {
        if (!ExtendTableUtil.isExistTable(tableName)) {
            return Responses.errorResponse("数据表不存在, 请重新创建");
        } else {
            List<Object> resultSet =  ExtendTableUtil.getOneRow(tableName, id);
            if (resultSet == null) {
                return Responses.errorResponse("数据表无该记录");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("data", resultSet);
            response.setData(hashMap);
            return response;
        }
    }

    /**
     * 向某个数据表中添加数据
     * @param map
     * @param tableName
     * @return
     */
    @PostMapping(value = "add/{tablename}")
    public Response addRow(@RequestBody Map<String, String> map, @PathVariable("tablename") String tableName) {
        if (!ExtendTableUtil.isExistTable(tableName)) {
            return Responses.errorResponse("数据表不存在, 请重新创建");
        } else {
            if (!ExtendTableUtil.insertRow(tableName, map)) {
                return Responses.errorResponse("插入数据失败");
            }
            return Responses.successResponse();
        }
    }

    /**
     * 删除数据表中的某些记录
     * @param tableName
     * @param id
     * @return
     */
    @DeleteMapping(value = "delete/{tablename}/{id}")
    public Response deleteRow(@PathVariable("tablename") String tableName, @PathVariable("id") String id) {
        if (!ExtendTableUtil.isExistTable(tableName)) {
            return Responses.errorResponse("数据表不存在, 请先创建");
        }
        if (!ExtendTableUtil.deleteRow(tableName, id)) {
            return Responses.errorResponse("删除失败");
        } else {
            return Responses.successResponse();
        }
    }

    /**
     * 修改数据表中的某些记录
     * @param tableName
     * @param id
     * @param values
     * @return
     */
    @PutMapping(value = "update/{tablename}/{id}")
    public Response updateRow(@PathVariable("tablename") String tableName, @PathVariable("id") String id, @RequestBody Map<String, String> values) {
        if (!ExtendTableUtil.isExistTable(tableName)) {
            return Responses.errorResponse("数据表不存在, 请先创建");
        } else {
            if (!ExtendTableUtil.updateRow(tableName, values, id)) {
                return Responses.errorResponse("修改失败");
            } else {
                return Responses.successResponse();
            }
        }
    }
}
