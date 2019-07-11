package it.sdeluca.activemqembedded.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the my_device database table.
 * Contiene le informazioni dei device registrati 
 */
@Entity
@Table(name="my_device")
@Getter
@Setter
@NoArgsConstructor
public class MyDevice implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="codice_fiscale")
	private String codiceFiscale;
	@Column(name="app_id")
	private String appId;
	@Column(name="app_version")
	private String appVersion;
	@Column(name="build")
	private String build;
	@Column(name="bundle_id")
	private String bundleId;
	@Column(name="device_token")
	private String deviceToken;
	@Column(name="local")
	private String local;
	@Column(name="os")
	private String os;
	@Column(name="os_version")
	private String osVersion;
	@Column(name="DT_REG")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtReg;
}