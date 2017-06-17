package it.alessiogaeta.surveymaster.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Provider
 *
 */
@Entity
@Table(name = "providers")
public class Provider implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@OneToMany(targetEntity = Survey.class)
	private String id;

	@Column(nullable = false)
	private String name;

	public Provider() {
		super();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
