package com.deep.api.resource;

import com.deep.domain.model.MoveRecord;
import com.deep.domain.service.MoveRecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.ws.RequestWrapper;

@RestController
@RequestMapping("/move")
public class MoveRecordController {

    @Resource
    private MoveRecordService service;

    @PostMapping("/insert")
    public void insert(@RequestBody MoveRecord moveRecord){
        service.insert(moveRecord);
    }
}
