/**
 * 
 */
package it.sdeluca.activemqembedded.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author B.Conetta
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class Device {

	private Long id;
	private String codiceFiscale;
	private String os;
	private String osVersion;
	private String bundleId;
	private String appVersion;
	private String build;
	private String deviceToken;  
	private String appId;
	private String local;
	private Date dtReg;
}
