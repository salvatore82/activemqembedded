/**
 * 
 */
package it.sdeluca.activemqembedded.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author a.pasca
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class Message {
	private String codiceFiscale;
	private Data data;
}
