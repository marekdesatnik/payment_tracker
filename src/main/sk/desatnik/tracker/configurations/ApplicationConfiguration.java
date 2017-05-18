package sk.desatnik.tracker.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ComponentConfiguration.class, SchedulingConfiguration.class})
public class ApplicationConfiguration {

}
