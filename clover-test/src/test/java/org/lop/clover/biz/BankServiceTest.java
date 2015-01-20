package org.lop.clover.biz;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.lop.clover.dal.entity.Bank;
import org.lop.modules.test.spring.Profiles;
import org.lop.modules.test.spring.SpringTransactionalTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

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

	@Test
	@Rollback( false )
	public void testCache() throws Exception {
		LoadingCache<String, Bank> cache = CacheBuilder.newBuilder().maximumSize( 100 ).expireAfterAccess( 10, TimeUnit.MINUTES )
				.build( new CacheLoader<String, Bank>() {
					@Override
					public Bank load( String key ) throws Exception {
						logger.info( ">>>>>>>>>db" );

						return bankService.get( key );
					}
				} );

		Bank bank = cache.get( "8a8a8a813f3c9c36013f3c9c3eea0001" );
		logger.info( ">>>1: {}", bank );

		bank = cache.get( "8a8a8a813f3c9c36013f3c9c3eea0001" );
		logger.info( ">>>2: {}", bank );

		bank.setRemark( bank.getRemark() + "_0" );
		bankService.update( bank );
		logger.info( ">>>3: {}", bank );

		bank = cache.get( "8a8a8a813f3c9c36013f3c9c3eea0001" );
		logger.info( ">>>4: {}", bank );

		cache.refresh( "8a8a8a813f3c9c36013f3c9c3eea0001" );
		bank = cache.get( "8a8a8a813f3c9c36013f3c9c3eea0001" );
		logger.info( ">>>5: {}", bank );
		
		cache.invalidateAll();
	}

}