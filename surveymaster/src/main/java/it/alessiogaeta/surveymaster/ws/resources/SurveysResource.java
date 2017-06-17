package it.alessiogaeta.surveymaster.ws.resources;

import java.util.Collection;

import javax.ws.rs.BeanParam;
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
import it.alessiogaeta.surveymaster.model.SurveyTemplate;

@Controller
@Path("/surveys")
@Produces("application/json")
public class SurveysResource {

	@Autowired
	private SurveysRepository repository;

	@PathParam("providerId")
	String providerId;

	@PathParam("subjectId")
	String subjectId;

	@GET
	public Response getAll(@BeanParam SurveyTemplate template) throws IllegalArgumentException, IllegalAccessException {
		try {
			Collection<Survey> list = null;
			if (providerId != null) {
				// As Providers sub-resource
				list = repository.findByProvider_Id(providerId);

			} else if (subjectId != null) {
				// As Subjects sub-resource
				list = repository.findBySubject_Id(Long.parseLong(subjectId));

			} else {
				list = repository.findAll(template.getQuerySpecifications());
			}

			if (list != null) {
				return Response.ok(list).build();

			} else {
				throw new NotFoundException();
			}

		} catch (final NumberFormatException e) {
			throw new NotFoundException();
		}
	}

	@GET
	@Path("/{id}")
	public Response getSurvey(@PathParam("id") String id) {
		try {
			Survey item = null;
			if (providerId == null && subjectId == null) {
				// Specific survey can only be accessed directly
				item = repository.findOne(Long.parseLong(id));
			}

			if (item != null) {
				return Response.ok(item).build();

			} else {
				throw new NotFoundException();
			}

		} catch (final NumberFormatException e) {
			throw new NotFoundException();
		}
	}

}
