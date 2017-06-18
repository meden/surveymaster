package it.alessiogaeta.surveymaster.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: SurveyOrder
 *
 */
@Entity
@Table(name = "survey_orders")
public class SurveyOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, insertable = false, updatable = false)
	private Requester requester;

	@Column(name = "requester_id")
	private Long requesterId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, insertable = false, updatable = false)
	private Survey survey;

	@Column(name = "survey_id")
	private Long surveyId;

	public SurveyOrder() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public Requester getRequester() {
		return this.requester;
	}

	public void setRequester(Requester requester) {
		this.requester = requester;
	}

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	@JsonIgnore
	public Survey getSurvey() {
		return this.survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

}