package hr.algebra.bibliosphereapi.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    private static final String Book_PRINT_JOB_IDENTITY = "BookPrintJob";
    private static final String Book_PRINT_TRIGGER = "BookPrintTrigger";

    @Bean
    public JobDetail DogPrintJobDetail() {
        return JobBuilder.newJob(BookPrintJob.class).withIdentity(Book_PRINT_JOB_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger DogPrintTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(30).repeatForever();

        return TriggerBuilder.newTrigger().forJob(DogPrintJobDetail())
                .withIdentity(Book_PRINT_TRIGGER).withSchedule(scheduleBuilder).build();
    }
}
