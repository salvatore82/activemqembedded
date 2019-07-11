/**
 * 
 */
package it.sdeluca.activemqembedded.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.sdeluca.activemqembedded.dto.FirebaseNotification;
import it.sdeluca.activemqembedded.dto.Message;
import it.sdeluca.activemqembedded.model.MyDevice;
import it.sdeluca.activemqembedded.repository.DeviceDao;
import lombok.extern.slf4j.Slf4j;

/**
 * @author a.pasca
 *
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService{
	

	@Value("${firebase.server.key}")
	private String serverKey;
	@Value("${firebase.api.url}")
	private String apiUrl;

	private static final String ACTIVEMQEMBEDDED = "activemqembedded";

	@Autowired
	DeviceDao deviceDao;
	@Autowired 
	JmsTemplate jmsTemplate;
	@Autowired
	ObjectMapper objectmapper;

	@Override
	public void send(Message message) throws Exception {
		MyDevice myDevice = new MyDevice();
		myDevice.setCodiceFiscale(message.getCodiceFiscale());
		List<MyDevice> myDeviceExamples = deviceDao.findAll(Example.of(myDevice));
		
		if(!myDeviceExamples.isEmpty()) {
			//Creazione oggetto da inviare al cloud firebase
			FirebaseNotification firebaseNotification = new FirebaseNotification();
			firebaseNotification.setData(message.getData());
			List<String> tokens = myDeviceExamples.stream().map(MyDevice::getDeviceToken).collect(Collectors.toList());
			firebaseNotification.setRegistration_ids(tokens);
			firebaseNotification.setPriority("high");
			log.debug("sending with convertAndSend() to queue");
	        jmsTemplate.convertAndSend(ACTIVEMQEMBEDDED, objectmapper.writeValueAsString(firebaseNotification));
		}
		else {
			log.error("Nessun dispositivo trovato con CF ".concat(message.getCodiceFiscale()));
			throw new Exception("Nessun dispositivo presente con CF".concat(message.getCodiceFiscale()));
		}
	}
	
	@JmsListener(destination = ACTIVEMQEMBEDDED)
    public void receiveMessage(@Payload String firebaseNotification) throws Exception {
		log.debug("received message " + firebaseNotification);
		try {
			//Chiamata post al servizio cloud messaging firebase
			HttpEntity<String> request = new HttpEntity<>(firebaseNotification);
			RestTemplate restTemplate = new RestTemplate();
			ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
			interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + serverKey));
			interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
			restTemplate.setInterceptors(interceptors);
			String firebaseResponse = restTemplate.postForObject(apiUrl, request, String.class);		
			log.info(firebaseResponse);
	    } catch (Exception e) {
	    	log.error("Errore nell'invio della notifica", e);
			throw e;
	    } 
    }
	
}
