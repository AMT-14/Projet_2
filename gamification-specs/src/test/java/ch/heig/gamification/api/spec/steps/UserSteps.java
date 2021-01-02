package ch.heig.gamification.api.spec.steps;

import ch.heig.gamification.ApiException;
import ch.heig.gamification.ApiResponse;
import ch.heig.gamification.api.DefaultApi;
import ch.heig.gamification.api.dto.Event;
import ch.heig.gamification.api.dto.User;
import ch.heig.gamification.api.spec.helpers.Environment;
import ch.heig.gamification.api.spec.helpers.GamificationObjects;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class UserSteps {

    private DefaultApi api;
    private Environment env;
    private GamificationObjects main;

    public UserSteps(GamificationObjects main, Environment env) {
        this.env = env;
        this.api = env.getApi();
        this.main = main;
        // authenticate API-Key
        String apiKey = env.getApiKey(); //api.getApiClient() .getAuthentication("X-API-KEY");// GamificationObjects.getApplication().getApiKey();
        api.getApiClient().setApiKey(apiKey);
    }

    @When("^I send a GET to the /users/\\{inGamifiedAppUserId} endpoint$")
    public void i_send_a_GET_to_the_UsersInGamifiedAppUserId_endpoint() {
        try {
            ApiResponse response = api.getUserWithHttpInfo(String.valueOf(main.getEvent().getInGamifiedAppUserId()));
            env.setApiResponse(response);
            // now process
            env.ApiResponseProcessor(env.getApiResponse());
        } catch (ApiException ex){
            env.ApiExceptionProcessor(ex);
        }
    }

    @Given("^I have an event payload and a user$")
    public void i_have_an_event_payload_and_a_user() {
        main.setEvent(new Event()
            .name("event 2319")
            .inGamifiedAppUserId("userID")
            .creationDateTime(Date.from(Instant.now()))
            .properties("eventType"));

        main.setUser(new User()
                // TODO userID ?
                .badges(new ArrayList<>())
        );
    }

    @Then("^I receive the correct user payload with id match$")
    public void i_receive_the_correct_user_payload_with_id_match() {
        assertEquals(main.getUser(), "userID");
    }

    @Given("^I have a wrong user$")
    public void i_have_a_wrong_user() {
        main.setUser(new User()
                // TODO userID ?
                .badges(new ArrayList<>())
        );
    }

    @When("^I send a GET to the \\/users endpoint$")
    public void i_send_a_GET_to_the_users_endpoint() {
        try {
            ApiResponse response = api.getUsersWithHttpInfo();
            env.setApiResponse(response);
            // now process
            env.ApiResponseProcessor(env.getApiResponse());
        } catch (ApiException ex){
            env.ApiExceptionProcessor(ex);
        }
    }
}
