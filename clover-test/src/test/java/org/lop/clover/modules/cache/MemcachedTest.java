package org.lop.clover.modules.cache;

import java.net.SocketAddress;
import java.util.Map;
import java.util.Map.Entry;

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

	@Test
	public void test() {

		Map<SocketAddress, Map<String, String>> map = this.spyMemcachedClient.getMemcachedClient().getStats();

		for ( Entry<SocketAddress, Map<String, String>> entry : map.entrySet() ) {
			logger.info( ">>>>>>>>>>>key: {}, value: {}", entry.getKey(), entry.getValue() );

			if ( null != entry.getValue() ) {
				for ( Entry<String, String> e : entry.getValue().entrySet() ) {

					logger.info( ">>>>>>>>>>>key: {}, value: {}", e.getKey(), e.getValue() );
				}
			}
		}
	}

}