package com.cepengagementservice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.cepengagementservice.Models.dto.BatchDTO;

public class BatchTest {
	
	@Test
	void test() {
		Batch batch = new Batch();
		Batch batchreturn = Batch.getBatchById("TR-1001");
		BatchDTO batchrerun = Batch.getBatchDTOById("TR-1001");
		assertEquals(batchreturn, batchreturn);
		assertEquals("TR-1001", batchreturn.batchId);
		assertEquals("Mock Batch 2", batchreturn.name);
		assertEquals("2016-01-01", batchreturn.startDate);
		assertEquals("2016-03-11", batchreturn.endDate);
		assertEquals("Java React", batchreturn.skill);
		assertEquals("West Virginia", batchreturn.location);
		assertEquals("Revature", batchreturn.type);
		assertEquals(70, batchreturn.goodGrade);
		assertEquals(80, batchreturn.passingGrade);
		batch.setBatchId("TR-1001");
		batch.setName("Mock Batch 2");
		batch.setStartDate("2016-01-01");
		batch.setEndDate("2016-03-11");
		batch.setSkill("Java React");
		batch.setLocation("West Virginia");
		batch.setType("Revature");
		batch.setGoodGrade(70);
		batch.setPassingGrade(80);
		assertEquals("TR-1001", batch.getBatchId());
		assertEquals("Mock Batch 2", batch.getName());
		assertEquals("2016-01-01", batch.getStartDate());
		assertEquals("2016-03-11", batch.getEndDate());
		assertEquals("Java React", batch.getSkill());
		assertEquals("West Virginia", batch.getLocation());
		assertEquals("Revature", batch.getType());
		assertEquals(70, batch.getGoodGrade());
		assertEquals(80, batch.getPassingGrade());
	}
}
