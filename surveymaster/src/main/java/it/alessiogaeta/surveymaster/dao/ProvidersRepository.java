package it.alessiogaeta.surveymaster.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alessiogaeta.surveymaster.model.Provider;

@Repository
public interface ProvidersRepository extends JpaRepository<Provider, String> {

}
