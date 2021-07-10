package org.sample.demo.log4jdemo.component;


import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class TestComponent1 {
	private static Logger logger = LogManager.getLogger(TestComponent1.class);

	public static int STEP_COUNT = 10;

	public void processStep(int STEP_COUNT) {
		Instant start = Instant.now();

		logger.info("*** TestComponent1 started ***");
		for (int i = 0; i < STEP_COUNT; i++) {

			logger.info("  Process step {} started.", i);
			try {
				Thread.sleep(10);
				logger.debug("  Process step {} detail debug message.", i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("  Process step {} completed.", i);
		}
		Instant end = Instant.now();
		logger.warn("*** TestComponent1 completed with {} ***" + Duration.between(start, end));

	}

}
