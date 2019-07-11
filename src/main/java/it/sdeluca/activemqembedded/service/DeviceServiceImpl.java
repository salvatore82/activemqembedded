package it.sdeluca.activemqembedded.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.sdeluca.activemqembedded.dto.Device;
import it.sdeluca.activemqembedded.model.MyDevice;
import it.sdeluca.activemqembedded.repository.DeviceDao;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	@Transactional
	public Long save(Device device) throws Exception {
		if(device == null) {
			throw new IllegalArgumentException("Dispositivo in input non valido");
		}
		MyDevice myDevice = new MyDevice();
		BeanUtils.copyProperties(device, myDevice, "id");
		try {
			Device deviceOut = new Device();
			deviceDao.save(myDevice);
			BeanUtils.copyProperties(myDevice, deviceOut);
			return deviceOut.getId();
		} catch (Exception e) {
			log.error("impossibile salvare dispositivo", e);
			throw e;
		}
	}
}
