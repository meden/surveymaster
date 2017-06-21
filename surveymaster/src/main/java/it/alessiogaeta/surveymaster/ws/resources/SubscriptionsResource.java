package it.alessiogaeta.surveymaster.ws.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.alessiogaeta.surveymaster.dao.SearchSubscriptionsRepository;
import it.alessiogaeta.surveymaster.model.SearchSubscription;

@Controller
@Path("/subscriptions")
@Produces(MediaType.APPLICATION_JSON)
public class SubscriptionsResource {

	/*
	 * There should be an authorization mechanism that can inject the actual
	 * requester identity. For the exercise purposes, using here a hardcoded
	 * identity.
	 */
	private static final Long REQUESTER_ID = 1L;

	@Context
	private HttpServletRequest request;

	@Autowired
	private SearchSubscriptionsRepository repository;

	@GET
	public Response getAll() throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(repository.findAllByRequesterId(REQUESTER_ID)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(SearchSubscription subscription) throws URISyntaxException {
		subscription.setRequesterId(REQUESTER_ID);
		subscription = repository.save(subscription);

		return Response.created(getResourceUri(subscription)).build();
	}

	@GET
	@Path("/{id}")
	public Response getOne(@PathParam("id") String id) {
		final SearchSubscription item = loadSubscripion(id);

		return Response.ok(item).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		final SearchSubscription item = loadSubscripion(id);

		repository.delete(item.getId());

		return Response.noContent().build();
	}

	private SearchSubscription loadSubscripion(String id) {
		SearchSubscription item = null;
		try {
			item = repository.findOne(Long.parseLong(id));
		} catch (final NumberFormatException e) {}
		if (item == null) {
			throw new NotFoundException();
		}
		return item;
	}

	private URI getResourceUri(SearchSubscription subscription) throws URISyntaxException {
		return new URI(request.getRequestURL().append("/").append(subscription.getId()).toString());
	}
}
