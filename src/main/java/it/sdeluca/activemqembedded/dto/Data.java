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
public class Data {
	
	private String title;
	private String message;
	private Long id;
}
