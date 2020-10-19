package com.java.pms.dispordrls.client;

import com.java.commonutils.api.APICODE;
import com.java.pms.dispordrls.entity.DispOrdRls;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-mes")
@Component
public interface MesDispOrdRlsClient {
    /**
     * 必须与MES中Controller方法一致
     * @param dispOrdRls
     * @return
     */
    @PostMapping("/mes/dispordrls/saveReleaseDispOrd")
    public APICODE saveReleaseDispOrd(@RequestBody DispOrdRls dispOrdRls);
}
