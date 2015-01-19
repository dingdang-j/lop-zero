package com.hz.modules.cache;

import org.junit.Test;
import org.lop.modules.cache.memcached.SpyMemcachedClient;
import org.lop.modules.test.spring.Profiles;
import org.lop.modules.test.spring.SpringContextTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles( Profiles.UNIT_TEST )
@ContextConfiguration( locations = { "/spring/applicationContext-scan-test.xml", "classpath*:/cache/applicationContext-scan-memcached.xml" } )
public class MemcachedTest extends SpringContextTestCase {

	@Autowired
	private SpyMemcachedClient spyMemcachedClient;

	@Test
	public void testGet() {
		String key = "k1";
		String value = "v1";

		String v = this.spyMemcachedClient.get( key );

		logger.info( v );

		this.spyMemcachedClient.set( key, 24 * 60 * 60, value );

		v = this.spyMemcachedClient.get( key );

		logger.info( v );
	}

}