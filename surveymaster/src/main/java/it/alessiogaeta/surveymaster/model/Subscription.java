package it.alessiogaeta.surveymaster.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.alessiogaeta.surveymaster.model.converters.ChannelsCollectionToStringConverter;

/**
 * The entity model for a survey.
 *
 * @author alessio
 *
 */
@Embeddable
public class Subscription implements Serializable {

	public static enum Channel {
		postal,
		mail,
		api,
		ftp;
	}

	public static enum Frequency {
		daily,
		weekly,
		monthly;
	}

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private Frequency frequency;

	@Convert(converter = ChannelsCollectionToStringConverter.class)
	private Collection<Channel> channels;

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public Collection<Channel> getChannels() {
		return channels;
	}

	public void setChannels(Collection<Channel> channels) {
		this.channels = channels;
	}

}
