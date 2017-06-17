package it.alessiogaeta.surveymaster.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alessiogaeta.surveymaster.model.Survey;

@Repository
public interface SurveysRepository extends JpaRepository<Survey, Long> {

}
