/**
 * 
 */
package it.sdeluca.activemqembedded.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import it.sdeluca.activemqembedded.dto.Message;
import it.sdeluca.activemqembedded.service.MessageService;

/**
 * @author a.pasca
 *
 */

@RestController
@RequestMapping(path = "/messages")
@Api(value = "Message Controller")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@PostMapping
	public void send(@RequestBody(required = true) @ApiParam("JSON message") Message message) throws Exception {
		messageService.send(message);
	}
}
