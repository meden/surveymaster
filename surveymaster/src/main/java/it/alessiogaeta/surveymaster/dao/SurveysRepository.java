package it.alessiogaeta.surveymaster.dao;

import org.springframework.data.repository.CrudRepository;

import it.alessiogaeta.surveymaster.model.Survey;

public interface SurveysRepository extends CrudRepository<Survey, Long> {

}
