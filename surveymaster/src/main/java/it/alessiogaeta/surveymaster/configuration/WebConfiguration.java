package it.alessiogaeta.surveymaster.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("it.alessiogaeta.surveymaster.controller")
public class WebConfiguration {

}
