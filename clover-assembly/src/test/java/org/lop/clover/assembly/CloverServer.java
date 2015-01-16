package org.lop.clover.assembly;

import org.lop.modules.test.jetty.JettyBootstrap;
import org.lop.modules.test.spring.Profiles;

/**
 * Jetty启动应用.
 * 
 * @author 潘瑞峥
 * @date 2015年1月7日
 */
public class CloverServer {

	public static void main( String[] args ) throws Exception {
		JettyBootstrap.start( 6835, Profiles.DEVELOPMENT );
	}

}