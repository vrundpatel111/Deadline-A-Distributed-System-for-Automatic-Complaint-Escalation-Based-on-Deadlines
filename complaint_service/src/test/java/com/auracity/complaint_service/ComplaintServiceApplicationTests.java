package com.auracity.complaint_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
	"spring.kafka.bootstrap-servers=localhost:65535",
	"spring.kafka.admin.fail-fast=false"
})
class ComplaintServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
