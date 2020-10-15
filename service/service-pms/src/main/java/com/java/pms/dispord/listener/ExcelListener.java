package com.java.pms.dispord.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.java.pms.dispord.entity.DispOrd;
import com.java.pms.dispord.service.DispOrdService;
import com.java.pms.dispord.vo.DispOrdData;
import org.springframework.beans.BeanUtils;

public class ExcelListener extends AnalysisEventListener<DispOrdData> {

    private DispOrdService dispOrdService;

    public ExcelListener(DispOrdService dispOrdService) {
        this.dispOrdService = dispOrdService;
    }
    public ExcelListener( ) {

    }

    /**
     * 每次读取一行执行此方法
     * @param dispOrdData
     * @param analysisContext
     */
    @Override
    public void invoke(DispOrdData dispOrdData, AnalysisContext analysisContext) {
        // copy 创建实体类
        DispOrd dispOrd = new DispOrd();
        //copy 数据
        BeanUtils.copyProperties(dispOrdData,dispOrd);
        //保存
        dispOrdService.saveOrUpdate(dispOrd);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
