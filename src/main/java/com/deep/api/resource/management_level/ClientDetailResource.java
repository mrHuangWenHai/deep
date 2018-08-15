package com.deep.api.resource.management_level;

import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.response.Response;
import com.deep.domain.service.management_level.ClientDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "client")
public class ClientDetailResource {
    @Resource
    private ClientDetailService clientDetailService;

    @GetMapping(value = "/{factory}")
    public Response readAll(HttpServletRequest request) {
        // 羊场为0， 代理为1，其他为2
        Byte flag = Byte.valueOf(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));
    }
}
