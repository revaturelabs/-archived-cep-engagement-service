package com.cepengagementservice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UserBatchTest {

	@Test
	void test() {
		UserBatch userbatch = new UserBatch(1, "TR-1000");
		assertEquals("TR-1000", userbatch.getBatchId());
		assertEquals(userbatch, userbatch);
	}
}
