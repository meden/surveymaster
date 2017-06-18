package it.alessiogaeta.surveymaster.model.converters;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.util.StringUtils;

import it.alessiogaeta.surveymaster.model.Subscription.Frequency;

@Converter
public class FrequenciesCollectionToStringConverter implements AttributeConverter<Collection<Frequency>, String> {

	@Override
	public String convertToDatabaseColumn(Collection<Frequency> attribute) {
		if (attribute == null || attribute.isEmpty()) {
			return "";
		}
		return StringUtils.arrayToCommaDelimitedString(attribute.toArray());
	}

	@Override
	public Collection<Frequency> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.trim().length() == 0) {
			return new ArrayList<>();
		}

		final Collection<Frequency> list = new ArrayList<>();
		for (final String string : dbData.split(",")) {
			list.add(Frequency.valueOf(string.trim().toLowerCase()));
		}
		return list;
	}

}
