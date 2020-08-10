package com.register.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

//import org.junit.Test;
import org.junit.jupiter.api.Test; // JUnit 5
import org.jvnet.mock_javamail.Mailbox;


public class SendEmailTests {


//	@Before
//	public void setUp() {
//		mail = new EmailSender();
//	}

	@Test
	void sendAsHtmlTest() throws MessagingException {
		EmailSender.sendAsHtml("test.dest@nutpan.com", "hit", "yooo");
		
		 List<Message> inbox = Mailbox.get("test.dest@nutpan.com");
		 
		 assertTrue(inbox.size() == 1);  
		  
	}

}
