package hr.algebra.bibliosphereapi.jobs;

import hr.algebra.bibliosphereapi.models.DogBreed;
import hr.algebra.bibliosphereapi.repository.DogRepository;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Set;

public class DogPrintJob extends QuartzJobBean {

    private final Logger log = LoggerFactory.getLogger(DogPrintJob.class);

    private final DogRepository DogRepository;

    public DogPrintJob(DogRepository DogRepository) {
        this.DogRepository = DogRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        final Set<DogBreed> DogSet = DogRepository.findAll();

        if(DogSet.stream().anyMatch(this::isDogAvailable)){
            log.info("The currently documented Dogs");
            log.info("------------------------------");
            DogSet.stream()
                    .filter(this::isDogAvailable)
                    .forEach(
                            Dog -> log.info(Dog.toString())
                    );
            log.info("------------------------------");
        } else {
            log.info("There are currently no documented Dogs");
        }

    }

    private boolean isDogAvailable(DogBreed dog) {
        return !dog.getBreedName().isEmpty();
    }
}
