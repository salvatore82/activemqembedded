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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.sdeluca.activemqembedded.dto.Device;
import it.sdeluca.activemqembedded.service.DeviceService;

/**
 * @author B.Conetta
 * Controller: Device
 */
@RestController
@RequestMapping(path = "/devices")
@Api(value = "Device Controller")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@PostMapping("")
	@ApiOperation("registra le informazioni di un dispositivo")
	public Long registraDevice(
			@RequestBody(required = true) @ApiParam("oggetto JSON che rappresenta il device") Device device)
			throws Exception {
		return deviceService.save(device);
	}

}
