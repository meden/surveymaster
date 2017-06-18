package it.alessiogaeta.surveymaster.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.alessiogaeta.surveymaster.dao.SearchSubscriptionsRepository;
import it.alessiogaeta.surveymaster.model.SearchSubscription;

@Controller
@Path("/subscriptions")
@Produces("application/json")
public class SubscriptionsResource {

	/*
	 * There should be an authorization mechanism that can inject the actual
	 * requester identity. For the exercise purposes, using here a hardcoded
	 * identity.
	 */
	private static final Long REQUESTER_ID = 1L;

	@Autowired
	private SearchSubscriptionsRepository repository;

	@GET
	public Response getAll() throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(repository.findAllByRequesterId(REQUESTER_ID)).build();
	}

	@GET
	@Path("/{id}")
	public Response getOne(@PathParam("id") String id) {
		final SearchSubscription item = repository.findOne(Long.parseLong(id));

		if (item != null) {
			return Response.ok(item).build();

		} else {
			throw new NotFoundException();
		}
	}
}
