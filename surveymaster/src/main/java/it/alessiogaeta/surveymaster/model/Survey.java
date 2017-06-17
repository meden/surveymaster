package it.alessiogaeta.surveymaster.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The entity model for a survey.
 *
 * @author alessio
 *
 */
@Entity
@Table(name = "surveys")
public class Survey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@ManyToOne
	Provider provider;

	@ManyToOne
	Subject subject;

	@Column(nullable = false)
	String gender;

	@Column(nullable = false)
	Integer ageMin;
	@Column(nullable = false)
	Integer ageMax;

	@Column(nullable = false)
	String incomeCurrency;
	@Column(nullable = false)
	Integer incomeMin;
	@Column(nullable = false)
	Integer incomeMax;

	@Column(nullable = false)
	String country;

	@Column(nullable = false)
	BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}

	public Integer getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(Integer ageMax) {
		this.ageMax = ageMax;
	}

	public String getIncomeCurrency() {
		return incomeCurrency;
	}

	public void setIncomeCurrency(String incomeCurrency) {
		this.incomeCurrency = incomeCurrency;
	}

	public Integer getIncomeMin() {
		return incomeMin;
	}

	public void setIncomeMin(Integer incomeMin) {
		this.incomeMin = incomeMin;
	}

	public Integer getIncomeMax() {
		return incomeMax;
	}

	public void setIncomeMax(Integer incomeMax) {
		this.incomeMax = incomeMax;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
