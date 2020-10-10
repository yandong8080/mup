package com.java.pms.dispord.service;

import com.java.commonutils.jpa.base.service.BaseService;
import com.java.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.java.pms.dispord.dao.DispOrdDao;
import com.java.pms.dispord.entity.DispOrd;
import com.java.pms.dispord.vo.DispOrdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DispOrdService extends BaseService<DispOrdDao,DispOrd> {

    @Autowired
    private DispOrdDao dispOrdDao;

    /**
     * 查询所有调度指令库
     * @return
     */
    public List<DispOrd> findAll() {
        return dispOrdDao.findAll();
    }

    /**
     * 带条件的分页查询
     * @param dispOrdQuery
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<DispOrd> findPageDispOed(DispOrdQuery dispOrdQuery,int pageNo, int pageSize){
        SimpleSpecificationBuilder<DispOrd> simpleSpecificationBuilder = new SimpleSpecificationBuilder<>();
        if (!StringUtils.isEmpty(dispOrdQuery.getOrderName())){
            simpleSpecificationBuilder.and("orderName",":",dispOrdQuery.getOrderName());
        }
        Page page = dispOrdDao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNo - 1, pageSize));
        return page;
    }

}
