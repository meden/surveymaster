package it.alessiogaeta.surveymaster.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.alessiogaeta.surveymaster.dao.ProvidersRepository;
import it.alessiogaeta.surveymaster.model.Provider;

@Controller
@Path("/providers")
@Produces("application/json")
public class ProvidersResource {

	@Autowired
	private ProvidersRepository repository;

	@GET
	public Response getAll() {
		return Response.ok(repository.findAll()).build();
	}

	@GET
	@Path("/{id}")
	public Response getOne(@PathParam("id") String id) {

		final Provider entity = repository.getOne(id);

		if (entity == null) {
			throw new NotFoundException();
		}

		return Response.ok(entity).build();
	}

	@Path("/{providerId}/surveys")
	public Class<SurveysResource> getSurveys() {
		return SurveysResource.class;
	}
}
