package com.java.mes.ppm.mppm.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "mes_month_pro")
@EntityListeners(AuditingEntityListener.class)
public class Mppm {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "generator_uuid", strategy = "uuid")
    @ApiModelProperty(value = "主键,采用hibernate的uuid生成32位字符串")
    private String id;

    @ApiModelProperty(value = "计划编号")
    @Column(name = "plan_code")
    private String planCode;

    @ApiModelProperty(value = "计划年份")
    @Column(name = "plan_year")
    private String planYear;

    @ApiModelProperty(value = "计划月份")
    @Column(name = "plan_month")
    private String planMonth;

    @ApiModelProperty(value = "计划单位")
    @Column(name = "plan_unit")
    private String planUnit;

    @ApiModelProperty(value = "总产量/万吨")
    @Column(name = "total_output")
    private BigDecimal totalOutput;


}
