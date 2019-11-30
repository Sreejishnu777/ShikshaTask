package cucumberOptions;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = "src/test/java/features",
		glue = "stepdefinitions",
		tags = "@HyperlinksTest",
		plugin = {"pretty","html:target/cucumber","json:target/cucumber.json","junit:target/cukes.xml"})


public class TestRunner extends AbstractTestNGCucumberTests{

}
