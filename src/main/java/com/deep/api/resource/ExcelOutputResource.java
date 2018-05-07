package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.domain.util.ExcelOutputUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * create by zhongrui on 18-5-5.
 */
@RestController
@RequestMapping("/excel")
public class ExcelOutputResource {

    /**
     * 为前端返回表格名称
     * @return 表格->下标
     */
    @Permit(authorities = "download_database")
    @RequestMapping(value = "/type",method = RequestMethod.GET)
    public Response type(){
        return ExcelOutputUtil.typeReturn();
    }

    @Permit(authorities = "download_database")
    @RequestMapping(value = "/download/{factoryNum}/{tableName}",method = RequestMethod.GET)
    public Response output(HttpServletResponse response,
                           @PathVariable("factoryNum") String factoryNum,
                           @PathVariable("tableName") String tableName) throws Exception{
        ExcelOutputUtil excelOutputUtil = new ExcelOutputUtil();
        return excelOutputUtil.excelOutput(response, factoryNum, tableName);
    }

}
