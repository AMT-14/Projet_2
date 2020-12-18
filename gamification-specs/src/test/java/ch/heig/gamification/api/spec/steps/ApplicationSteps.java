package ch.heig.gamification.api.spec.steps;

import ch.heig.gamification.api.spec.helpers.Environment;
import ch.heig.gamification.ApiException;
import ch.heig.gamification.ApiResponse;
import ch.heig.gamification.api.DefaultApi;
import ch.heig.gamification.api.dto.Application;
import ch.heig.gamification.api.spec.helpers.GamificationObjects;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationSteps {

    private DefaultApi api;
    private Environment env;
    private GamificationObjects main;

//
    public ApplicationSteps(GamificationObjects main,Environment env) {
        this.env = env;
        this.api = env.getApi();
        this.main = main;
    }

    @Given("there is an Application server")
    public void there_is_an_Application_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have an application payload$")
    public void i_have_an_application_payload() throws Throwable {
        Application application = new Application();
        application.setName("Application42");
        main.setApplication(application);
    }

    @When("^I POST it to the /applications endpoint$")
    public void i_POST_it_to_the_applications_endpoint() throws Throwable {
        try {
            // System.out.println(api.registerApplicationWithHttpInfo(main.getApplication()));
            ApiResponse response = api.registerApplicationWithHttpInfo(main.getApplication());
            env.setApiResponse(response);
            // now process
            env.ApiResponseProcessor(env.getApiResponse());
            GamificationObjects.setApplication((Application) env.getApiResponse().getData());
        } catch (ApiException ex){
            env.ApiExceptionProcessor(ex);
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int expectedStatusCode) throws Throwable {
        assertEquals(expectedStatusCode, env.getHttpStatus());
    }

//    @When("^I ask for a list of registered applications with a GET on the /registrations endpoint$")
//    public void i_ask_for_a_list_of_registered_applications_with_a_GET_on_the_registrations_endpoint() throws Throwable {
//        applications = api.applicationsGet();
//    }

    @Then("^I see my application in the list$")
    public void i_see_my_application_in_the_list() throws Throwable {
        Application expected = new Application();
        expected.setName(main.getApplication().getName());

        assert(expected.getName()).equals("Application42");

    }
//
//    @When("I send a GET to the URL in the location header")
//    public void iSendAGETToTheURLInTheLocationHeader() {
//        Integer id = Integer.parseInt(lastReceivedLocationHeader.substring(lastReceivedLocationHeader.lastIndexOf('/') + 1));
//        try {
//            lastApiResponse = api.getApplicationWithHttpInfo(id);
//            processApiResponse(lastApiResponse);
//            lastReceivedApplication = (Application) lastApiResponse.getData();
//        } catch (ApiException e) {
//            processApiException(e);
//        }
//    }
//
//    @And("I receive a payload that is the same as the application payload")
//    public void iReceiveAPayloadThatIsTheSameAsTheApplicationPayload() {
//        assertEquals(application, lastReceivedApplication);
//    }
//
//    private void processApiResponse(ApiResponse apiResponse) {
//        lastApiResponse = apiResponse;
//        lastApiCallThrewException = false;
//        lastApiException = null;
//        lastStatusCode = lastApiResponse.getStatusCode();
//        List<String> locationHeaderValues = (List<String>)lastApiResponse.getHeaders().get("Location");
//        lastReceivedLocationHeader = locationHeaderValues != null ? locationHeaderValues.get(0) : null;
//    }
//
//    private void processApiException(ApiException apiException) {
//        lastApiCallThrewException = true;
//        lastApiResponse = null;
//        lastApiException = apiException;
//        lastStatusCode = lastApiException.getCode();
//    }

}
