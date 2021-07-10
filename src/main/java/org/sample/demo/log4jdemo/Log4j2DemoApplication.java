package org.sample.demo.log4jdemo;

import ch.qos.logback.classic.LoggerContext;

import org.sample.demo.log4jdemo.component.TestComponent1;
import org.sample.demo.log4jdemo.component.TestComponent2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class Log4j2DemoApplication {
	private static final Logger log = LogManager.getLogger(org.sample.demo.log4jdemo.Log4j2DemoApplication.class);

	@Autowired
	private static Environment environment;

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(org.sample.demo.log4jdemo.Log4j2DemoApplication.class, args);

		Integer val1 = Integer.valueOf(System.getenv("PARAM1"));
		Integer val2 = Integer.valueOf(System.getenv("PARAM2"));


		if (val1 == null  || val2 == null ){
			log.error("Param1 or Param2 is not defined in environment");
			throw  new Exception( "Param1 or Param2 is not defined in environment");
		}


		log.info("info....");
		log.debug("debug....");
		log.warn("warn....");
		log.error("error....");


		ExecutorService executorService = null;
		TestComponent1 testCom1 = context.getBean(TestComponent1.class);
		TestComponent2 testCom2 = context.getBean(TestComponent2.class);
		//testCom1.processStep(val1);
		//testCom2.processStep(val2);

		Runnable runnable1 = () -> {
			System.err.println("started thread1");
			log.info("started thread1");
			testCom1.processStep(val1);
			log.info("ended thread1");
			System.err.println("ended thread1");

		};
		Runnable runnable2 = () -> {
			System.err.println("started thread2");
			log.info("started thread2");
			testCom2.processStep(val2);
			log.info("ended thread2");
			System.err.println("ended thread2");
		};

		//new Thread(runnable1).start();
		//new Thread(runnable2).start();

		try{
			executorService = Executors.newFixedThreadPool(2);
			executorService.submit(runnable1);
			executorService.submit(runnable2);
		}catch (Exception e ){
			log.info(e.getMessage());
		}
		finally{
			executorService.shutdown();
		}

		//LoggerContext loggerContext = (LoggerContext) LogManager.getContext();
		//loggerContext.stop();
	}

}
