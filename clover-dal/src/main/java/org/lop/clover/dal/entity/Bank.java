package org.lop.clover.dal.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.lop.modules.repository.hibernate.BaseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 银行实体.
 * 
 * @author 潘瑞峥
 * @date 2013-6-2
 */
@Entity
@Table( name = "lop_bank" )
public class Bank extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 编号. */
	private String code;

	/** 名称. */
	private String name;

	/** 资产. */
	private Double asset;

	/** 备注. */
	private String remark;

	/** 创建日期. */
	@JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00" )
	private Date createTime = new Date();

	public String getCode() {
		return code;
	}

	public void setCode( String code ) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Double getAsset() {
		return asset;
	}

	public void setAsset( Double asset ) {
		this.asset = asset;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark( String remark ) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

}