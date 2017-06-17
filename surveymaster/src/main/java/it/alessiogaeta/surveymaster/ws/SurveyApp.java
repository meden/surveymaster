package it.alessiogaeta.surveymaster.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("a")
public class SurveyApp extends ResourceConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyApp.class);

	public SurveyApp() {
		LOGGER.info("Init SurveyMaster WS");
		packages("it.alessiogaeta.surveymaster.ws.resources");

		register(JacksonFeature.class);
	}
}
