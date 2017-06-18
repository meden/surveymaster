package it.alessiogaeta.surveymaster.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alessiogaeta.surveymaster.model.SurveyOrder;

@Repository
public interface SurveyOrdersRepository extends JpaRepository<SurveyOrder, Long> {

	Collection<SurveyOrder> findAllByRequesterId(Long requesterId);

}
