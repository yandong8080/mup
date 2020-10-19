package com.java.mes.dispordrls.controller;

import com.java.commonutils.api.APICODE;

import com.java.mes.dispordrls.entity.DispOrdRls;
import com.java.mes.dispordrls.service.DispOrdRlsService;
import com.java.mes.dispordrls.vo.DispOrdRlsQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "煤矿调度指令管理")
@RestController
@RequestMapping("/mes/dispordrls")
@CrossOrigin
public class DispOrdRlsController {

    @Autowired
    private DispOrdRlsService dispOrdRlsService;

    @ApiOperation(value = "根据条件进行分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPageDispOrdRls(@RequestBody(required = false) DispOrdRlsQuery dispOrdRlsQuery, @PathVariable(name = "pageNo") int pageNo, @PathVariable(name = "pageSize") int pageSize){
        Page<DispOrdRls> page = dispOrdRlsService.findPageDispOrdRls(dispOrdRlsQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<DispOrdRls> dispOrdRlsList = page.getContent();
        return APICODE.OK().data("total",totalElements).data("items",dispOrdRlsList);
    }

    @ApiOperation(value = "接收集团下达的调度指令管理信息", notes = "等待被PMS服务所调用的一个方法")
    @PostMapping("saveReleaseDispOrd")
    public APICODE saveReleaseDispOrd(@RequestBody DispOrdRls dispOrdRls){
        dispOrdRlsService.saveOrUpdate(dispOrdRls);
        return APICODE.OK();
    }
}
