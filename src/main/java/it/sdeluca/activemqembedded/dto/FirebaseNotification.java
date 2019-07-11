/**
 * 
 */
package it.sdeluca.activemqembedded.dto;

import java.util.List;

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
public class FirebaseNotification {
	private Data data;
	private List<String> registration_ids;
	private String priority;
}
