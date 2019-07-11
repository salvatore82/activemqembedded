package it.sdeluca.activemqembedded.config;

import java.util.Arrays;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.store.jdbc.JDBCAdapter;
import org.apache.activemq.store.jdbc.JDBCPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJms
@Configuration
@EnableScheduling
@Profile("!test")
public class JmsConfig {

	@Autowired
	protected DataSource dataSource;

	@Value("${jms.queue.url}")
	private String queueUrl;
	@Value("${jms.broker.name}")
	protected String brokerName;
	@Value("${jms.config.redeliverydelay}")
	private Long redeliveryDelay;
	@Value("${jms.config.maxredeliveries}")
	private Integer maxRedeliveries;
	
	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(queueUrl);
		factory.setTrustedPackages(Arrays.asList("it.sdeluca.activemqembedded"));
		RedeliveryPolicy policy = factory.getRedeliveryPolicy();
		policy.setInitialRedeliveryDelay(redeliveryDelay);
		policy.setRedeliveryDelay(redeliveryDelay);			// intervallo di retry
		policy.setMaximumRedeliveries(maxRedeliveries); 	// tentativi prima di entrare in DLQ
		factory.setRedeliveryPolicy(policy);
		return factory;
	}

	@Bean
	public PersistenceAdapter jdbcPersistenceAdapter() {
		JDBCPersistenceAdapter jdbcPersistenceAdapter = new JDBCPersistenceAdapter();
		jdbcPersistenceAdapter.setDataSource(dataSource);
		jdbcPersistenceAdapter.setBrokerName(brokerName);
		jdbcPersistenceAdapter.setUseLock(false);
		jdbcPersistenceAdapter.setAdapter(jdbcAdapter());
		return jdbcPersistenceAdapter;
	}

	@Bean
	public JDBCAdapter jdbcAdapter() {
		return new CustomPostgresqlJDBCAdapter();
	}

	@Bean
	public BrokerService brokerService() throws Exception {
		BrokerService brokerService = new BrokerService();
		brokerService.setPersistent(true);
		brokerService.setPersistenceAdapter(jdbcPersistenceAdapter());
		brokerService.setBrokerName(brokerName);
		brokerService.addConnector(queueUrl);
		return brokerService;
	}
}