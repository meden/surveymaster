package it.alessiogaeta.surveymaster.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.alessiogaeta.surveymaster.model.Survey;

@Repository
public interface SurveysRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {

	public List<Survey> findByProvider_Id(String providerId);

	public List<Survey> findBySubject_Id(Long subjectId);

	public Survey findByIdAndProvider_Id(Long id, String providerId);

	public Survey findByIdAndSubject_Id(Long id, Long subjectId);

}
