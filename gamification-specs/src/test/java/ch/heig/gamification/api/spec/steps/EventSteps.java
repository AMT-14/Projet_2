package ch.heig.gamification.api.spec.steps;

import ch.heig.gamification.ApiException;
import ch.heig.gamification.ApiResponse;
import ch.heig.gamification.api.DefaultApi;
import ch.heig.gamification.api.dto.Event;
import ch.heig.gamification.api.spec.helpers.Environment;
import ch.heig.gamification.api.spec.helpers.GamificationObjects;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;

public class EventSteps {

    private DefaultApi api;
    private Environment env;
    private GamificationObjects main;


    public EventSteps(GamificationObjects main,Environment env) {
        this.env = env;
        this.api = env.getApi();
        this.main = main;
        // authenticate API-Key
        String apiKey = env.getApiKey(); //api.getApiClient() .getAuthentication("X-API-KEY");// GamificationObjects.getApplication().getApiKey();
        api.getApiClient().setApiKey(apiKey);
    }

    @Given("^I have an event payload$")
    public void i_have_an_event_payload() throws Throwable {
        Event event = new Event()
                .name("event 2319")
                .inGamifiedAppUserId("userID")
                .creationDateTime(Date.from(Instant.now()))
                .properties("eventType");

        main.setEvent(event);
    }

    @When("^I POST it to the /events endpoint$")
    public void i_POST_it_to_the_events_endpoint() throws Throwable {
        try {
            ApiResponse response = api.registerEventWithHttpInfo(main.getEvent());
            env.setApiResponse(response);
            // now process
            env.ApiResponseProcessor(env.getApiResponse());
        } catch (ApiException ex){
            env.ApiExceptionProcessor(ex);
        }
    }

}
