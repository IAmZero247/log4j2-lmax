package org.sample.demo.log4jdemo.component;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/

import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;

@Component
public class TestComponent2 {

	private static final String DEFINED_LOGGER = "org.sample.demo.log4jdemo.component.TestComponent2";
	private static final Logger lOG = LogManager.getLogger(DEFINED_LOGGER);
	public static int STEP_COUNT = 10;

	public void processStep(int STEP_COUNT) {
		Instant start = Instant.now();
	 
		lOG.info("*** TestComponent2 started ***");
		for (int i = 0; i < STEP_COUNT; i++) {

			lOG.info("  Process step {} started.", i);
			try {
				Thread.sleep(10);
				lOG.debug("  Process step {} detail debug message.", i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lOG.info("  Process step {} completed.", i);
		}
		Instant end = Instant.now();
		lOG.warn("*** TestComponent2 completed with {} ***" + Duration.between(start, end));

	}

}
