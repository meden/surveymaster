package it.alessiogaeta.surveymaster.ws.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BadRequestException;
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

import it.alessiogaeta.surveymaster.dao.SurveyOrdersRepository;
import it.alessiogaeta.surveymaster.model.SurveyOrder;
import it.alessiogaeta.surveymaster.model.SurveyOrder.OrderStatus;

@Controller
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrdersResource {

	/*
	 * There should be an authorization mechanism that can inject the actual
	 * requester identity. For the exercise purposes, using here a hardcoded
	 * identity.
	 */
	private static final Long REQUESTER_ID = 1L;

	@Context
	private HttpServletRequest request;

	@Autowired
	private SurveyOrdersRepository repository;

	@GET
	public Response getAll() throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(repository.findAllByRequesterId(REQUESTER_ID)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(SurveyOrder order) throws URISyntaxException {
		order.setRequesterId(REQUESTER_ID);
		order = repository.save(order);

		return Response.created(getResourceUri(order)).build();
	}

	@GET
	@Path("/{id}")
	public Response getOne(@PathParam("id") String id) {
		final SurveyOrder item = loadOrder(id);

		return Response.ok(item).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		final SurveyOrder item = loadOrder(id);

		if (!item.getStatus().equals(OrderStatus.pending)) {
			throw new BadRequestException("too-late-mate");
		}

		repository.delete(item.getId());

		return Response.noContent().build();
	}

	private URI getResourceUri(SurveyOrder subscription) throws URISyntaxException {
		return new URI(request.getRequestURL().append("/").append(subscription.getId()).toString());
	}

	private SurveyOrder loadOrder(String id) {
		SurveyOrder item = null;
		try {
			item = repository.findOne(Long.parseLong(id));
		} catch (final NumberFormatException e) {}
		if (item == null) {
			throw new NotFoundException();
		}
		return item;
	}
}
