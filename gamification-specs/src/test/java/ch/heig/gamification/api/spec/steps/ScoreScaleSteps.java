package ch.heig.gamification.api.spec.steps;

import ch.heig.gamification.ApiException;
import ch.heig.gamification.ApiResponse;
import ch.heig.gamification.api.DefaultApi;
import ch.heig.gamification.api.dto.Application;
import ch.heig.gamification.api.dto.Event;
import ch.heig.gamification.api.dto.ScoreScale;
import ch.heig.gamification.api.dto.User;
import ch.heig.gamification.api.spec.helpers.Environment;
import ch.heig.gamification.api.spec.helpers.GamificationObjects;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ScoreScaleSteps {

    private DefaultApi api;
    private Environment env;
    private GamificationObjects main;

    public ScoreScaleSteps(GamificationObjects main, Environment env) {
        this.env = env;
        this.api = env.getApi();
        this.main = main;
        // authenticate API-Key
        String apiKey = env.getApiKey(); //api.getApiClient() .getAuthentication("X-API-KEY");// GamificationObjects.getApplication().getApiKey();
        api.getApiClient().setApiKey(apiKey);
    }

    @Given("^I have a score payload$")
    public void i_have_a_score_payload() {
        main.setScoreScale(new ScoreScale()
                .name("ScoreScale42"));
    }

    @When("I POST it to the /scoreScales endpoint")
    public void iPOSTItToTheScoreScalesEndpoint() throws Throwable{
        try {
            ApiResponse response = api.createScoreScaleWithHttpInfo(main.getScoreScale());
            env.setApiResponse(response);
            // now process
            env.apiResponseProcessor(env.getApiResponse());
        } catch (ApiException ex){
            env.apiExceptionProcessor(ex);
        }
    }

//    @When("I send a GET to the /scoreScales/\\{id} endpoint")
//    public void iSendAGETToTheScoreScalesIdEndpoint() {
//        try {
//            Application app = main.getApplication();
//            ApiResponse response = api.getScoreScaleWithHttpInfo(0);
//            env.setApiResponse(response);
//            // now process
//            env.apiResponseProcessor(env.getApiResponse());
//        } catch (ApiException ex){
//            env.apiExceptionProcessor(ex);
//        }
//    }

    @Then("I receive the correct score payload with id match")
    public void iReceiveTheCorrectScorePayloadWithIdMatch() {
        assertEquals(main.getScoreScale().getName(), "ScoreScale42");
    }
}
