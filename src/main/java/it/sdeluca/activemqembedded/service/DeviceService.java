/**
 * 
 */
package it.sdeluca.activemqembedded.service;

import it.sdeluca.activemqembedded.dto.Device;

/**
 * @author B.Conetta
 *
 */
public interface DeviceService {
	/**
	 * Salva le informazioni di un dispositivo sul db.
	 * @Param Device - dispositivo da registrare
	 * @return Id - del dispositivo registrato
	 * @throws Exception
	 */
	public Long save(Device device) throws Exception;

}
