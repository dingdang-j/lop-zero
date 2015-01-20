package org.lop.clover.biz;

import org.junit.Test;
import org.lop.clover.dal.entity.Bank;
import org.lop.modules.test.spring.Profiles;
import org.lop.modules.test.spring.SpringTransactionalTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles( Profiles.UNIT_TEST )
@ContextConfiguration( locations = { "classpath*:/**/applicationContext-scan*.xml" } )
public class BankServiceTest extends SpringTransactionalTestCase {

	@Autowired
	private BankService bankService;

	@Test
	public void testGet() {
		Bank bank = this.bankService.get( "8a8a8a813f3c9c36013f3c9c3ef90004" );

		logger.info( ">>>>>>>>{}", bank );
	}

}