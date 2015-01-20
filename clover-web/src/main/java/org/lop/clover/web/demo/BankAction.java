package org.lop.clover.web.demo;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.lop.clover.dal.dao.BankDao;
import org.lop.clover.dal.entity.Bank;
import org.lop.clover.web.base.CrudActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 银行控制层.
 * 
 * @author 潘瑞峥
 * @date 2013-6-2
 */
@Results( { @Result( name = CrudActionSupport.RELOAD, location = "bank", type = "redirect" ) } )
public class BankAction extends CrudActionSupport<Bank, String> {

	private static final long serialVersionUID = 1L;

	@Autowired
	public void setDao( BankDao bankDao ) {
		super.dao = bankDao;
	}

}