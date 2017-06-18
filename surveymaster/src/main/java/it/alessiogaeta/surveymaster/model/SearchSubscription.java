package it.alessiogaeta.surveymaster.model;

import java.io.Serializable;

import javax.persistence.Embedded;
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
 * Entity implementation class for Entity: SearchSubscription
 *
 */
@Entity
@Table(name = "search_subscriptions")
public class SearchSubscription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requesterid", nullable = false, insertable = false, updatable = false)
	private Requester requester;

	private Long requesterId;

	@Embedded
	private Subscription subscription;

	@Embedded
	private SurveyTemplate template;

	public SearchSubscription() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public SurveyTemplate getTemplate() {
		return this.template;
	}

	public void setTemplate(SurveyTemplate template) {
		this.template = template;
	}

	public Subscription getSubscription() {
		return this.subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

}
