package it.alessiogaeta.surveymaster.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alessiogaeta.surveymaster.model.SearchSubscription;

@Repository
public interface SearchSubscriptionsRepository extends JpaRepository<SearchSubscription, Long> {

	Collection<SearchSubscription> findAllByRequesterId(Long requesterId);

}
