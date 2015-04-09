package es.uniovi.asw.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format 		= { "pretty", "html:target/cucumber" },
        glue 		= "es.uniovi.asw.steps",
        features 	= "classpath:cucumber/"
)
public class RunGameTest {}