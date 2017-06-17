package it.alessiogaeta.surveymaster.model;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class SurveySpecification implements Specification<Survey> {

	private final SearchCriterion criterion;

	public SurveySpecification(SearchCriterion criterion) {
		super();
		this.criterion = criterion;
	}

	@Override
	public Predicate toPredicate(Root<Survey> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (criterion.getOperation().equalsIgnoreCase(">")) {
			return builder.greaterThanOrEqualTo(
			        root.<String>get(criterion.getKey()), criterion.getValue().toString());

		} else if (criterion.getOperation().equalsIgnoreCase("<")) {
			return builder.lessThanOrEqualTo(
			        root.<String>get(criterion.getKey()), criterion.getValue().toString());

		} else if (criterion.getOperation().equalsIgnoreCase(":")) {
			if (root.get(criterion.getKey()).getJavaType() == String.class) {
				return builder.like(
				        root.<String>get(criterion.getKey()), "%" + criterion.getValue() + "%");
			} else {
				return builder.equal(root.get(criterion.getKey()), criterion.getValue());
			}

		} else if (criterion.getOperation().equalsIgnoreCase("|")
		        && criterion.getValue() instanceof Collection) {

			final In<Object> in = builder.in(root.get(criterion.getKey()));
			for (final Object obj : (Collection<?>) criterion.getValue()) {
				in.value(obj);
			}

			return in;
		}

		return null;
	}

}
