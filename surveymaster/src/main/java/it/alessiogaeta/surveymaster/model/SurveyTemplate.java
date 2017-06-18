package it.alessiogaeta.surveymaster.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specifications;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.alessiogaeta.surveymaster.model.converters.StringCollectionToStringConverter;

@Embeddable
public class SurveyTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Convert(converter = StringCollectionToStringConverter.class)
	private Collection<String> providers;

	@Convert(converter = StringCollectionToStringConverter.class)
	private Collection<String> subjects;

	@QueryParam("gender")
	private String gender;

	@QueryParam("ageMin")
	private Integer ageMin;

	@QueryParam("ageMax")
	private Integer ageMax;

	@Convert(converter = StringCollectionToStringConverter.class)
	private Collection<String> incomeCurrencies;

	@QueryParam("incomeMin")
	private Integer incomeMin;

	@QueryParam("incomeMax")
	private Integer incomeMax;

	@Convert(converter = StringCollectionToStringConverter.class)
	private Collection<String> countries;

	@Transient
	@QueryParam("priceMin")
	private BigDecimal priceMin;

	@Transient
	@QueryParam("priceMax")
	private BigDecimal priceMax;

	public Collection<String> getProviders() {
		return providers;
	}

	@QueryParam("providers")
	@JsonIgnore
	public void setProviders(String providers) {
		if (providers != null) {
			this.providers = new HashSet<>(Arrays.asList(providers.split(",")));
		}
	}

	@JsonDeserialize(as = HashSet.class)
	public void setProviders(HashSet<String> providers) {
		this.providers = providers;
	}

	public Collection<String> getSubjects() {
		return subjects;
	}

	@QueryParam("subjects")
	@JsonIgnore
	public void setSubjects(String subjects) {
		if (subjects != null) {
			this.subjects = new HashSet<>(Arrays.asList(subjects.split(",")));
		}
	}

	@JsonDeserialize(as = HashSet.class)
	public void setSubjects(HashSet<String> subjects) {
		this.subjects = subjects;
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

	public Collection<String> getIncomeCurrencies() {
		return incomeCurrencies;
	}

	@QueryParam("incomeCurrencies")
	@JsonIgnore
	public void setIncomeCurrencies(String incomeCurrencies) {
		if (incomeCurrencies != null) {
			this.incomeCurrencies = new HashSet<>(Arrays.asList(incomeCurrencies.split(",")));
		}
	}

	@JsonDeserialize(as = HashSet.class)
	public void setIncomeCurrencies(HashSet<String> incomeCurrencies) {
		this.incomeCurrencies = incomeCurrencies;
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

	public Collection<String> getCountries() {
		return countries;
	}

	@QueryParam("countries")
	@JsonIgnore
	public void setCountries(String countries) {
		if (countries != null) {
			this.countries = new HashSet<>(Arrays.asList(countries.split(",")));
		}
	}

	@JsonDeserialize(as = HashSet.class)
	public void setCountries(HashSet<String> countries) {
		this.countries = countries;
	}

	public BigDecimal getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(BigDecimal priceMin) {
		this.priceMin = priceMin;
	}

	public BigDecimal getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(BigDecimal priceMax) {
		this.priceMax = priceMax;
	}

	@JsonIgnore
	public Specifications<Survey> getQuerySpecifications()
	        throws IllegalArgumentException, IllegalAccessException {

		// TODO (Alessio): user reflection (and eventually generics)
		final List<SurveySpecification> surveySpecs = new ArrayList<>();
		if (this.providers != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("provider", "|", this.providers)));
		}
		if (this.subjects != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("subject", "|", this.subjects)));
		}
		if (this.gender != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("gender", ":", this.gender)));
		}
		if (this.ageMin != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("ageMin", ">", this.ageMin)));
		}
		if (this.ageMax != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("ageMax", "<", this.ageMax)));
		}
		if (this.incomeCurrencies != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("incomeCurrency", "|", this.incomeCurrencies)));
		}
		if (this.incomeMin != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("incomeMin", ">", this.incomeMin)));
		}
		if (this.incomeMax != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("incomeMax", "<", this.incomeMax)));
		}
		if (this.countries != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("country", "|", this.countries)));
		}
		if (this.priceMin != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("price", ">", this.priceMin)));
		}
		if (this.priceMax != null) {
			surveySpecs.add(new SurveySpecification(new SearchCriterion("price", "<", this.priceMax)));
		}

		Specifications<Survey> specifications = null;
		if (surveySpecs.isEmpty()) {
			specifications = Specifications.where(null);
		} else {
			specifications = Specifications.where(surveySpecs.get(0));
			for (int i = 1; i < surveySpecs.size(); i++) {
				specifications = specifications.and(surveySpecs.get(i));
			}
		}

		return specifications;
	}
}
