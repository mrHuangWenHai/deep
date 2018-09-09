package com.deep.api.resource.management_level;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.service.management_level.ClientDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping(value = "client")
public class ClientDetailResource {
    @Resource
    private ClientDetailService clientDetailService;

    @GetMapping(value = "/{factory}/role/{flag}")
    public Response readAll(@PathVariable("factory") Long factory, @PathVariable("flag") Byte flag) {
        // 羊场为0，代理为1，其他为2
        if (flag == 2) {
            return Responses.errorResponse("无权限");
        } else {
            HashMap<String, Object> data = new HashMap<>();
            data.put("detail", clientDetailService.getAllDetails(factory, flag));
           return  Responses.successResponse(data);
        }
    }
}
