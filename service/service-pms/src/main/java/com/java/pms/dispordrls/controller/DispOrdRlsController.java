package com.java.pms.dispordrls.controller;

import com.java.commonutils.api.APICODE;
import com.java.pms.dispordrls.client.MesDispOrdRlsClient;
import com.java.pms.dispordrls.entity.DispOrdRls;
import com.java.pms.dispordrls.service.DispOrdRlsService;
import com.java.pms.dispordrls.vo.DispOrdRlsQuery;
import com.java.servicebase.hanler.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "集团调度指令管理")
@RestController
@RequestMapping("/pms/dispordrls")
@CrossOrigin
public class DispOrdRlsController {

    @Autowired
    private DispOrdRlsService dispOrdRlsService;

    @Autowired
    private MesDispOrdRlsClient mesDispOrdRlsClient;

    @ApiOperation(value = "根据条件进行分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPageDispOrdRls(@RequestBody(required = false) DispOrdRlsQuery dispOrdRlsQuery, @PathVariable(name = "pageNo") int pageNo, @PathVariable(name = "pageSize") int pageSize){
        Page<DispOrdRls> page = dispOrdRlsService.findPageDispOrdRls(dispOrdRlsQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<DispOrdRls> dispOrdRlsList = page.getContent();
        return APICODE.OK().data("total",totalElements).data("items",dispOrdRlsList);
    }

    @ApiOperation(value = "根据ID获取调度指令管理信息")
    @GetMapping("{dispOrdRlsId}")
    public APICODE getDispOrdRlsById(@PathVariable(name = "dispOrdRlsId") String dispOrdRlsId){
        DispOrdRls dispOrdRls = dispOrdRlsService.getById(dispOrdRlsId);
        return APICODE.OK().data("dispOrdRls",dispOrdRls);
    }

    @ApiOperation(value = "下达集团调度指令")
    @PutMapping("updateRelease/{dispOrdRlsId}")
    public APICODE updateRelease(@PathVariable(name = "dispOrdRlsId") String dispOrdRlsId){
        DispOrdRls dispOrdRls = dispOrdRlsService.getById(dispOrdRlsId);
        // 调用MES服务的接口
        dispOrdRls.setReleaseTime(new Date());
        dispOrdRls.setIsRelease(1);
        APICODE apicode = mesDispOrdRlsClient.saveReleaseDispOrd(dispOrdRls);
        if (apicode.getCode()==20001){
            throw new MyException(20001,apicode.getMessage());
        }
        // 管理pms中的数据
        dispOrdRlsService.saveOrUpdate(dispOrdRls);
        return APICODE.OK();
    }
}
