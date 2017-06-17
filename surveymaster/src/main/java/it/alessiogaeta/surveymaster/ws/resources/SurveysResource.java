package it.alessiogaeta.surveymaster.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.alessiogaeta.surveymaster.dao.SurveysRepository;
import it.alessiogaeta.surveymaster.model.Survey;

@Controller
@Path("/surveys")
@Produces("application/json")
public class SurveysResource {

	@Autowired
	private SurveysRepository repository;

	@GET
	public Response getAll() {
		return Response.ok(repository.findAll()).build();
	}

	@GET
	@Path("/{id}")
	public Response getSurvey(@PathParam("id") String id) {
		Survey item = null;
		try {
			item = repository.findOne(Long.parseLong(id));
		} catch (final NumberFormatException e) {}

		if (item == null) {
			throw new NotFoundException();
		}

		return Response.ok(item).build();
	}
}
