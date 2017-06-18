package it.alessiogaeta.surveymaster.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Requester
 *
 */
@Entity
@Table(name = "requesters")
public class Requester implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@OneToMany(targetEntity = SearchSubscription.class)
	private Long requesterId;

	@Column(nullable = false)
	private String name;

	// FIXME (Alessio): should be a class, but it's an exercise after all... :)
	private String address;

	private String email;

	private String api;

	private String ftp;

	public Requester() {
		super();
	}

	public Long getRequesterId() {
		return this.requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApi() {
		return this.api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getFtp() {
		return this.ftp;
	}

	public void setFtp(String ftp) {
		this.ftp = ftp;
	}

}
