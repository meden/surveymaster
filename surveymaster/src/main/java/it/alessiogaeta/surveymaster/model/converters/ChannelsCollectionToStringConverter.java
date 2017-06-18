package it.alessiogaeta.surveymaster.model.converters;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.util.StringUtils;

import it.alessiogaeta.surveymaster.model.Subscription.Channel;

@Converter
public class ChannelsCollectionToStringConverter implements AttributeConverter<Collection<Channel>, String> {

	@Override
	public String convertToDatabaseColumn(Collection<Channel> attribute) {
		if (attribute == null || attribute.isEmpty()) {
			return "";
		}
		return StringUtils.arrayToCommaDelimitedString(attribute.toArray());
	}

	@Override
	public Collection<Channel> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.trim().length() == 0) {
			return new ArrayList<>();
		}

		final Collection<Channel> list = new ArrayList<>();
		for (final String string : dbData.split(",")) {
			list.add(Channel.valueOf(string.trim().toLowerCase()));
		}
		return list;
	}

}
