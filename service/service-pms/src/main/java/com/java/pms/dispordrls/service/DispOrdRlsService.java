package com.java.pms.dispordrls.service;

import com.alibaba.excel.util.StringUtils;
import com.java.commonutils.jpa.base.service.BaseService;
import com.java.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.java.pms.dispordrls.dao.DispOrdRlsDao;
import com.java.pms.dispordrls.entity.DispOrdRls;
import com.java.pms.dispordrls.vo.DispOrdRlsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DispOrdRlsService extends BaseService<DispOrdRlsDao, DispOrdRls> {
    /**
     * 根据条件进行分页查询
     * @param dispOrdRlsQuery
     * @param pageNo
     * @param pageSize
     * @return
     */

    @Autowired
    private DispOrdRlsDao dispOrdRlsDao;
    public Page<DispOrdRls> findPageDispOrdRls(DispOrdRlsQuery dispOrdRlsQuery,int pageNo,int pageSize){
        // 封装查询条件
        SimpleSpecificationBuilder<DispOrdRls> simpleSpecificationBuilder = new SimpleSpecificationBuilder();
        if (!StringUtils.isEmpty(dispOrdRlsQuery.getOrderNo())){
            simpleSpecificationBuilder.and("OrderNo",":",dispOrdRlsQuery.getOrderNo());
        }
        Page page = dispOrdRlsDao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNo - 1, pageSize));
        return page;
    }

}
