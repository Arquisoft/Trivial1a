package es.uniovi.asw.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format 		= { "pretty", "html:target/cucumber" },
        glue 		= "es.uniovi.asw.steps",
        features 	= "classpath:cucumber/"
)
public class RunGameTest {
}
