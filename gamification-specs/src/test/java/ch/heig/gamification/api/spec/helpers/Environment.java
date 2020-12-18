package ch.heig.gamification.api.spec.helpers;

import ch.heig.gamification.ApiException;
import ch.heig.gamification.ApiResponse;
import ch.heig.gamification.api.DefaultApi;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;

public class Environment {

    private DefaultApi api = new DefaultApi();

    @Getter
    @Setter
    int HTTPStatus;

    @Getter
    @Setter
    boolean isException;

    @Getter
    @Setter
    ApiResponse apiResponse;

    @Getter
    @Setter
    ApiException apiException;



    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heig.gamification.server.url");
        api.getApiClient().setBasePath(url);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public DefaultApi getApi() {
        return api;
    }

    public void ApiExceptionProcessor(ApiException e){
        isException = true;
        apiException = e;
        HTTPStatus = e.getCode();

        // response should be removed in case of exception
        apiResponse = null;
    }

    public void ApiResponseProcessor(ApiResponse r){
        apiResponse = r;
        HTTPStatus = apiResponse.getStatusCode();

        // exception should be negated in this case
        isException = false;
        apiException = null;
    }


    public int getHTTPStatus() {
        return this.HTTPStatus;
    }
}
