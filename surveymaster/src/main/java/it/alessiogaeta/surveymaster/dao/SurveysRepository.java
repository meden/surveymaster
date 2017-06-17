package it.alessiogaeta.surveymaster.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.alessiogaeta.surveymaster.model.Survey;

@Repository
public interface SurveysRepository extends CrudRepository<Survey, Long> {

}
