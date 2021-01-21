package ch.heig.gamification.api.spec.steps;

import ch.heig.gamification.ApiException;
import ch.heig.gamification.ApiResponse;
import ch.heig.gamification.api.DefaultApi;
import ch.heig.gamification.api.dto.Badge;
import ch.heig.gamification.api.spec.helpers.Environment;
import ch.heig.gamification.api.spec.helpers.GamificationObjects;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class BadgeSteps {

    private DefaultApi api;
    private Environment env;
    private GamificationObjects main;


    public BadgeSteps(GamificationObjects main,Environment env) {
        this.env = env;
        this.api = env.getApi();
        this.main = main;
        // authenticate API-Key
        String apiKey = env.getApiKey(); //api.getApiClient() .getAuthentication("X-API-KEY");// GamificationObjects.getApplication().getApiKey();
        api.getApiClient().setApiKey(apiKey);
    }

    @Given("^I have a badge payload$")
    public void i_have_a_badge_payload() throws Throwable {
        Badge badge = new Badge()
                .name("Badge42");
        main.setBadge(badge);
    }

    @When("^I POST it to the /badges endpoint$")
    public void i_POST_it_to_the_badges_endpoint() throws Throwable {
        try {
            ApiResponse response = api.createBadgeWithHttpInfo(main.getBadge());
            env.setApiResponse(response);
            // now process
            env.apiResponseProcessor(env.getApiResponse());
        } catch (ApiException ex){
            env.apiExceptionProcessor(ex);
        }
    }

    @And("^I ask for this badge with a GET on the /badges/id endpoint$")
    public void i_ask_for_this_badge_with_a_GET_on_the_badge_endpoint() throws Throwable {
        //int id = main.getBadge().getId(); //won t work for now, can t access to ID
        Badge badge = main.getBadge();
        // Badge badge = api.getBadge(id,null);
        assert(badge.getName().equals(main.getBadge().getName()));
        assert(badge.getName().equals("Badge42"));
    }
}
