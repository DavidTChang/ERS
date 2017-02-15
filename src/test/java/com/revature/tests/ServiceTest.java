package com.revature.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.enums.Status;

public class ServiceTest {

	@Test
	public void approvedTest() {
		assertEquals(Status.APPROVED.getId(), 2);
	}
	@Test
	public void declinedTest() {
		assertEquals(Status.DECLINED.getId(), 3);
	}
	@Test
	public void pendingTest() {
		assertEquals(Status.PENDING.getId(), 1);
	}

}
