package com.java.pms.dispord.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pms_disp_ord")
@EntityListeners(AuditingEntityListener.class)
public class DispOrd {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "generator_uuid", strategy = "uuid")
    @ApiModelProperty(value = "主键,采用hibernate的uuid生成32位字符串")
    private String id;

    @ApiModelProperty(value = "指令名称")
    @Column(name = "order_name")
    private String orderName;

    @ApiModelProperty(value = "指令类型")
    @Column(name = "spec_type")
    private int specType;

    @ApiModelProperty(value = "指令优先级")
    @Column(name = "priority")
    private int priority;

    @ApiModelProperty(value = "指令描述")
    @Column(name = "order_desc")
    private String orderDesc;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTm+8")
    @ApiModelProperty(value = "创建时间")
    @Column(name = "gmt_create",nullable = false,updatable = false)
    private Date gmtCreate;


    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    @Column(name = "gmt_modified",nullable = false,insertable = false)
    private Date gmtModified;

}
