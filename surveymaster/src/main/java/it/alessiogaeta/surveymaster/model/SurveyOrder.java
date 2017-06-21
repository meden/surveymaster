package it.alessiogaeta.surveymaster.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.alessiogaeta.surveymaster.model.Subscription.Channel;

/**
 * Entity implementation class for Entity: SurveyOrder
 *
 */
@Entity
@Table(name = "survey_orders")
public class SurveyOrder implements Serializable {

	public enum OrderStatus {
		pending,
		paid,
		processing,
		delivered
	}

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

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Channel channel;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.pending;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar created;

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

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

}
