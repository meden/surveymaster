package it.alessiogaeta.surveymaster.model.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.util.StringUtils;

@Converter
public class StringCollectionToStringConverter implements AttributeConverter<Collection<String>, String> {

	@Override
	public String convertToDatabaseColumn(Collection<String> attribute) {
		if (attribute == null || attribute.isEmpty()) {
			return "";
		}
		return StringUtils.arrayToCommaDelimitedString(attribute.toArray());
	}

	@Override
	public Collection<String> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.trim().length() == 0) {
			return new ArrayList<>();
		}

		final String[] data = dbData.split(",");
		return Arrays.asList(data);
	}

}
