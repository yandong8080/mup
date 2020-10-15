package com.java.pms.dispord.controller;

import com.alibaba.excel.EasyExcel;
import com.java.pms.dispord.entity.DispOrd;
import com.java.pms.dispord.listener.ExcelListener;
import com.java.pms.dispord.service.DispOrdService;
import com.java.commonutils.api.APICODE;
import com.java.pms.dispord.vo.DispOrdData;
import com.java.pms.dispord.vo.DispOrdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "调度指令库管理 - 控制层")
@RestController
@RequestMapping("/pms/dispord")
@CrossOrigin //解决跨域问题
public class DispOrdController {

    @Autowired
    private DispOrdService dispOrdService;

    /**
     * 查询所有调度指令库
     * @return 指令库
     */
    @ApiOperation(value = "查询所有指令库")
    @GetMapping
    public APICODE findAll() {
        List<DispOrd> dispOrdList = dispOrdService.findAll();
         //制造系统异常
         //int i = 1/0;
        return APICODE.OK().data("items", dispOrdList);
    }

    @ApiOperation(value = "带条件的分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPageDispOrd(@RequestBody(required = false)DispOrdQuery dispOrdQuery, @PathVariable(name = "pageNo") int pageNo, @PathVariable(name = "pageSize") int pageSize){
        Page<DispOrd> page = dispOrdService.findPageDispOed(dispOrdQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<DispOrd> dispOrdList = page.getContent();
        return APICODE.OK().data("total",totalElements).data("items",dispOrdList);
    }

    @ApiOperation(value = "创建(新增)调用指令库管理")
    @PostMapping("saveDispOrd")
    public APICODE saveDispOrd(@RequestBody DispOrd dispOrd){
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID获取调度指令库")
    @GetMapping("{dispOrdId}")
    public APICODE getDispOrdById(@PathVariable(name = "dispOrdId") String dispOrdId) {
        DispOrd dispOrd = dispOrdService.getById(dispOrdId);
        return APICODE.OK().data("dispOrd", dispOrd);
    }

    @ApiOperation(value = "根据ID修改调度指令库管理")
    @PutMapping("updateDispOrd")
    public APICODE updateDispOrd(@RequestBody DispOrd dispOrd){
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID删除调度指令库")
    @DeleteMapping("{dispOrdId}")
    public APICODE deleteDispOrdById(@PathVariable(name = "dispOrdId") String dispOrdId) {
        dispOrdService.removeById(dispOrdId);
        return APICODE.OK();
    }

    @ApiOperation(value = "列表导出", notes = "使用阿里EasyExcel导出Excel格式的列表数据")
    @GetMapping("exportEasyExcel")
    public void exportEasyExcel(HttpServletResponse response) {
        // ## 查询调度指令库
        List<DispOrd> dispOrdList = dispOrdService.findAll();
        // ## 定义导出列表集合
        List<DispOrdData> dispOrdDataList = new ArrayList<>();

        for (DispOrd dispOrd : dispOrdList) {
            // 创建封装类
            DispOrdData dispOrdData = new DispOrdData();
            // copy数据
            BeanUtils.copyProperties(dispOrd, dispOrdData);
            //添加对象到集合中
            dispOrdDataList.add(dispOrdData);
        }
        //导出 Excel
        String fileName = "dispOrdList";

        try {
            // ## 设置响应信息
            response.reset();
            response.setContentType("application/vnd.ms-excel; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + ".xlsx");
            EasyExcel.write(response.getOutputStream(), DispOrdData.class).sheet("指令列表").doWrite(dispOrdDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "列表导入", notes = "使用阿里EasyExcel导入Excel格式的列表数据")
    @PostMapping("importEasyExcel")
    public void importEasyExcel(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), DispOrdData.class, new ExcelListener(dispOrdService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "列表导出", notes = "使用POI导出Excel格式的列表数据")
    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) {
        dispOrdService.exportExcel(response);
    }

    @ApiOperation(value = "列表导入", notes = "使用POI导入Excel格式的列表数据")
    @PostMapping("importExcel")
    public void importExcel(MultipartFile file) {
        dispOrdService.importExcel(file);
    }

}
