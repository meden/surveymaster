package it.alessiogaeta.surveymaster.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.alessiogaeta.surveymaster.dao.SubjectsRepository;
import it.alessiogaeta.surveymaster.model.Subject;

@Controller
@Path("/subjects")
@Produces("application/json")
public class SubjectsResource {

	@Autowired
	private SubjectsRepository repository;

	@GET
	public Response getAll() {
		return Response.ok(repository.findAll()).build();
	}

	@GET
	@Path("/{id}")
	public Response getOne(@PathParam("id") String id) {
		Subject entity = null;
		try {
			entity = repository.findOne(Long.parseLong(id));
		} catch (final NumberFormatException e) {}

		if (entity == null) {
			throw new NotFoundException();
		}

		return Response.ok(entity).build();
	}
}
