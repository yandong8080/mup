package com.java.pms.dispordrls.client;

import com.java.commonutils.api.APICODE;
import com.java.pms.dispordrls.entity.DispOrdRls;
import org.springframework.stereotype.Component;

@Component
public class MesDispOrdRlsDegradeClient implements MesDispOrdRlsClient {
    @Override
    public APICODE saveReleaseDispOrd(DispOrdRls dispOrdRls) {
        return APICODE.ERROR().message("Mes服务调用失败 - 下达错误");
    }
}
