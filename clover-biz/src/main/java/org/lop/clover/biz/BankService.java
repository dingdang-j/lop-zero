package org.lop.clover.biz;

import org.lop.clover.dal.dao.BankDao;
import org.lop.clover.dal.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

	@Autowired
	private BankDao bankDao;

	public Bank get( String id ) {
		return this.bankDao.get( id );
	}

}