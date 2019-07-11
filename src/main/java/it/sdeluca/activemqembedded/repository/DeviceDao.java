/**
 * 
 */
package it.sdeluca.activemqembedded.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sdeluca.activemqembedded.model.MyDevice;

/**
 * @author B.Conetta
 *
 */
@Repository
public interface DeviceDao extends JpaRepository<MyDevice, Long> {
 
}
