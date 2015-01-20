package org.lop.clover.modules.mail;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.lop.modules.mail.MimeMailService;
import org.lop.modules.test.spring.Profiles;
import org.lop.modules.test.spring.SpringContextTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.icegreen.greenmail.util.GreenMail;

/**
 * 邮件测试.
 */
@ActiveProfiles( Profiles.UNIT_TEST )
@ContextConfiguration( locations = { "classpath*:/**/applicationContext-scan*.xml" } )
public class MailServiceTest extends SpringContextTestCase {

	@Autowired
	private MimeMailService mimeMailService;

	@Autowired
	private GreenMail greenMail;

	@Test
	public void testSendTextMail() throws Exception {
		String from = "greenmail@localhost.com";
		String to = "prz@taohuichang.com";
		String subject = "dingdang测试主题";
		String content = "dingdang测试内容";

		mimeMailService.sendTextMail( new String[] { to }, subject, content );

		greenMail.waitForIncomingEmail( 2000, 1 );

		MimeMessage[] messages = greenMail.getReceivedMessages();
		MimeMessage message = messages[ messages.length - 1 ];

		Assertions.assertThat( ( ( InternetAddress ) message.getFrom()[ 0 ] ).getAddress() ).isEqualTo( from );
		Assertions.assertThat( message.getSubject() ).isEqualTo( subject );
		Assertions.assertThat( ( ( String ) message.getContent() ) ).contains( content );
	}

}