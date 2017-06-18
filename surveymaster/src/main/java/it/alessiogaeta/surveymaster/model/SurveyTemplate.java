package it.alessiogaeta.surveymaster.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specifications;

public class SurveyTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<String> providers;

	private Set<String> subjects;

	@QueryParam("gender")
	private String gender;

	@QueryParam("ageMin")
	private Integer ageMin;

	@QueryParam("ageMax")
	private Integer ageMax;

	private Set<String> incomeCurrencies;

	@QueryParam("incomeMin")
	private Integer incomeMin;

	@QueryParam("incomeMax")
	private Integer incomeMax;

	private Set<String> countries;

	@QueryParam("priceMin")
	private BigDecimal priceMin;

	@QueryParam("priceMax")
	private BigDecimal priceMax;

	public Set<String> getProviders() {
		return providers;
	}

	@QueryParam("providers")
	public void setProviders(String providers) {
		if (providers != null) {
			this.providers = new HashSet<>(Arrays.asList(providers.split(",")));
		}
	}

	public Set<String> getSubjects() {
		return subjects;
	}

	@QueryParam("subjects")
	public void setSubjects(String subjects) {
		if (subjects != null) {
			this.subjects = new HashSet<>(Arrays.asList(subjects.split(",")));
		}
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

	public Set<String> getIncomeCurrencies() {
		return incomeCurrencies;
	}

	@QueryParam("incomeCurrencies")
	public void setIncomeCurrencies(String incomeCurrencies) {
		if (incomeCurrencies != null) {
			this.incomeCurrencies = new HashSet<>(Arrays.asList(incomeCurrencies.split(",")));
		}
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

	public Set<String> getCountries() {
		return countries;
	}

	@QueryParam("countries")
	public void setCountries(String countries) {
		if (countries != null) {
			this.countries = new HashSet<>(Arrays.asList(countries.split(",")));
		}
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
