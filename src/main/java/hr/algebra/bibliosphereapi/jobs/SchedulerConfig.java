package hr.algebra.bibliosphereapi.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    private static final String Dog_PRINT_JOB_IDENTITY = "DogPrintJob";
    private static final String Dog_PRINT_TRIGGER = "DogPrintTrigger";

    @Bean
    public JobDetail DogPrintJobDetail() {
        return JobBuilder.newJob(DogPrintJob.class).withIdentity(Dog_PRINT_JOB_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger DogPrintTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(30).repeatForever();

        return TriggerBuilder.newTrigger().forJob(DogPrintJobDetail())
                .withIdentity(Dog_PRINT_TRIGGER).withSchedule(scheduleBuilder).build();
    }
}
