package it.sdeluca.activemqembedded;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqembeddedApplicationTests {

	@Value("${jms.broker.name}")
	private String brokerName;

	@Test
	public void contextLoads() {
	}

}
