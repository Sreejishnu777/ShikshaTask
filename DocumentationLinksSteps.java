package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.paulhammant.ngwebdriver.NgWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;


public class DocumentationLinksSteps 
{
	WebDriver driver;
	JavascriptExecutor js;
    NgWebDriver ngDriver;
    HttpURLConnection httpConnect;
	String documentationPage,url = "";
    int responseCode = 200,actualResponseCode;
    
		@Before
		public void beforeTest() throws Throwable {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ File.separator+"config/chromedriver.exe");
			driver = new ChromeDriver();
	        js = (JavascriptExecutor)driver;
	        ngDriver = new NgWebDriver(js);
	        driver.manage().window().maximize();
		}
	 	@Given("^User loads \"([^\"]*)\"$")
	    public void user_loads_something(String homePage) throws Throwable {
	 		documentationPage = homePage;
	 		driver.get(documentationPage);
	    }

	    @Then("^verify page loads properly and angular is initialized$")
	    public void verify_page_loads_properly_and_angular_is_initialized() throws Throwable {
	    	ngDriver.waitForAngularRequestsToFinish();//this method ensures web page is loaded properly & thus AngularJS starts automatically, thereby angular is initialized.
	    }

	    @And("^verify all links on page internal to documentation site are not broken and for each link if page loads and angular is initialized$")
	    public void verify_all_links_on_page__internal_to_documentation_site_are_not_broken_and_for_each_link_if_page_loads_and_angular_is_initialized() throws Throwable {
	    	try
	    	{
//	    		List<WebElement> links = driver.findElements(By.tagName("a"));//capture all the links elements in to an array list
	    		List<WebElement> links = driver.findElements(By.xpath("//a[starts-with(@href,'/documentation')]"));
	    		int linksCount = links.size();//number of links
//	    		int counter = 0;
		        for(int i=0;i<linksCount;i++) 
		        {
	        	    url = links.get(i).getAttribute("href");
		            if(url.isEmpty() || url == null)
		            {
						System.out.println("URL " +url+ " is not configured for anchor tag or it is empty");
		                continue;//to skip following steps and to go back to while loop
		            }
		            if(!url.startsWith(documentationPage))
		            {
		                System.out.println("URL " +url+ " does not belongs to Documentation, skipping it.");
		                continue;//to skip following steps and to go back to while loop
		            }
		            try
		            {
		            	httpConnect = (HttpURLConnection)(new URL(url).openConnection());
		                httpConnect.setRequestMethod("HEAD");
		                httpConnect.connect();
		                responseCode = httpConnect.getResponseCode();
		                if(responseCode >= 400)//all invalid response codes are greater than or equal to 400
		                {
		                    System.out.println("URL " +url+ " is a broken link");
		                    Assert.assertTrue(false);//If there is a broken link for documentation which doesn't load, test will be failed here.
		                }
		                else
		                {
		                    System.out.println("URL " +url+ " is a valid link");
//		                    counter++;
		                    driver.get(url);
		                    String currentURL = driver.getCurrentUrl();
		                    httpConnect = (HttpURLConnection)(new URL(currentURL).openConnection());
			                httpConnect.setRequestMethod("HEAD");
			                httpConnect.connect();
			                actualResponseCode = httpConnect.getResponseCode();
			                Assert.assertEquals(actualResponseCode, responseCode);
		                    ngDriver.waitForAngularRequestsToFinish();//Ensure page is fully loaded & so that hence angular is initialized
		                    if(!driver.getCurrentUrl().equals(documentationPage))
		                    {
		                    	driver.navigate().back();//to go back to the documentation home page
		                    }
		                    ngDriver.waitForAngularRequestsToFinish();//Ensure page is fully loaded 
		                    links = driver.findElements(By.xpath("//a[starts-with(@href,'/documentation')]"));// defining web element to handle stale element reference exception
		                } 
		            }
	                catch (MalformedURLException e) 
	                {
	                	System.out.println(e);
	                } 
	                catch (IOException e) 
	                {
	                	System.out.println(e);
	                } 
		        }
//		        System.out.println("Count of valid links: "+counter);
		    }
            catch (Exception e) 
            {
            	System.out.println(e);
            	Assert.assertTrue(false);
            }
	    }
	    
	    @After
		public void afterTest(Scenario scenario) throws Throwable 
	    {
			String scenarioStatus =  scenario.getStatus();
			if(!scenario.isFailed())
			{
				System.out.println("TEST COMPLETED, STATUS: " + scenarioStatus.toUpperCase());
			}
			else
			{
				scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
				System.out.println("TEST COMPLETED, STATUS: " + scenarioStatus.toUpperCase());
			}
	    	driver.quit();
		}

}
	    	
	    
