package ch.heig.gamification.api.endpoints;


import ch.heig.gamification.api.model.Application;
import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.api.ApplicationsApi;
import ch.heig.gamification.repositories.ApplicationRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;



import javax.validation.Valid;

@Controller
public class ApplicationsApiController implements ApplicationsApi {

    @Autowired
    ApplicationRepository applicationRepository;

    public ResponseEntity<String> registerApplication(@ApiParam(value = "", required = true) @Valid @RequestBody Application application) {
        ApplicationEntity newApplicationEntity = toApplicationEntity(application);
        applicationRepository.save(newApplicationEntity);
        return ResponseEntity.ok(newApplicationEntity.getApiKey().toString());
    }

    private ApplicationEntity toApplicationEntity(Application application){
        ApplicationEntity entity = new ApplicationEntity();
        entity.setName(application.getName());
        return entity;
    }

    private Application toApplication(ApplicationEntity applicationEntity){
        Application application = new Application();
        application.setName(applicationEntity.getName());
        return application;
    }

}
