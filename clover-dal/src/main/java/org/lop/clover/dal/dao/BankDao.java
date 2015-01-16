package org.lop.clover.dal.dao;

import org.lop.clover.dal.entity.Bank;
import org.lop.modules.repository.hibernate.SimpleHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * 银行Dao.
 * 
 * @author 潘瑞峥
 * @date 2013-6-2
 */
@Repository
public class BankDao extends SimpleHibernateDao<Bank, String> {}