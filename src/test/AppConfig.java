import com.elevatormgr.web.controller.ElevatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * Created by raghuram gururajan on 11/5/16.
 */
@Configuration
public class AppConfig {

    @Bean
    public ElevatorService getElevatorService() {
        return  new ElevatorService();
    }
}
