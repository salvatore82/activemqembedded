/**
 * 
 */
package it.sdeluca.activemqembedded.service;

import it.sdeluca.activemqembedded.dto.Message;

/**
 * @author a.pasca
 *
 */
public interface MessageService {
	
	public void send(Message message) throws Exception;
}
