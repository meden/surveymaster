package it.alessiogaeta.surveymaster.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alessiogaeta.surveymaster.model.Subject;

@Repository
public interface SubjectsRepository extends JpaRepository<Subject, Long> {

}
